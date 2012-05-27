package com.util.export;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.tools.Log;


public class AccessImp implements AccessRead {
	
	private String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	private String dbUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};";
	private Connection conn = null;
	private Statement  stmt = null;
	private String filename = null;
	private String dirname = null;
	
	//表名
	private List<String> listName = new ArrayList<String>();
	//表头数据
	ArrayList<String> tableHeadList = new ArrayList<String>();
	//表数据
	ArrayList<String>  dataList = new ArrayList<String>();
	
    public static void main(String[] args){
    	AccessImp access = new AccessImp();
    	access.read("mobile.mdb","dd");
    	
    }
	
	public void read(String dbName,String dirname){
		
		init(dbName,dirname);
		getAllTableName();
		
		try {
		     getTableData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//初始化 
    private void init(String dbName,String dirname){
    
    	this.dirname = dirname;
    	dbUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};";
    	dbUrl = dbUrl +"DBQ="+dbName;
    	filename = dbName;
    	listName.clear();
    	tableHeadList.clear();
    	dataList.clear();
    	try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    	
    }
    
   //获取所有表名
	private void getAllTableName(){
		try {
			System.out.println("dbUrl-->"+dbUrl);
			conn = DriverManager.getConnection(dbUrl);
			
			stmt=conn.createStatement();    
			DatabaseMetaData  dbmd=conn.getMetaData();  
			ResultSet  rs=dbmd.getTables(null,null,"%",null); 
			String tablename = null;
			while(rs.next()){  
				tablename = rs.getString(3);
				System.out.println("table->"+tablename);
				if(!tablename.startsWith("MSys") &&
					!tablename.contentEquals("~"))
			     listName.add(tablename);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
			if(conn!= null){
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				      
			  e.printStackTrace();
		} 
		
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
           
	}
	
	//获取所有表头字段，根据表名
	private void getTableData() throws SQLException, IOException{
		String sql5 = null;
		try {
			conn = DriverManager.getConnection(dbUrl);
			stmt=conn.createStatement();
		    int tableLoop = 0;
		    
			for(tableLoop = 0; tableLoop < listName.size(); tableLoop++){
				tableHeadList.clear();
				sql5="select * from "+ listName.get(tableLoop);
			    ResultSet rs=stmt.executeQuery(sql5);  
			    ResultSetMetaData metaDate   =   rs.getMetaData();   
			    int   number   =   metaDate.getColumnCount();   
			    String[]   column   =   new   String[number];   
			    for   (int   j   =   0;   j   <   column.length;   j++)   {   
			       column[j]   =   metaDate.getColumnName(j   +   1);  
			       tableHeadList.add(column[j].toString());
			       System.out.println("xxxx :"+column[j].toString());
			    } 
			    //一行行读取数据
			    while(rs.next()){
			    	System.out.println();
			    	dataList.clear();
			    	String data = "";
			    	for(int loop = 0; loop < column.length; loop++){
			    	//	System.out.println(tableHeadList.get(loop));
			    		data = "";
			    		data=rs.getString(tableHeadList.get(loop));
			    		
			    		System.out.print(" "+data);
			    		dataList.add(data);
			    	}
			    	CusteomerExport.anasys(dirname,filename, tableHeadList,dataList,false);
			    }
			    //打印表头记录
			 //   FileUtil.headInfoWrite(filename, listName.get(tableLoop), tableHeadList);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("execute "+sql5 +" failed");
			stmt.close();    
		    conn.close();
		   // FileUtil.headInfoErr(filename);
			e.printStackTrace();
		}
		stmt.close();    
	    conn.close();
	}
	

}

