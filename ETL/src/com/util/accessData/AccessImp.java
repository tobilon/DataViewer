package com.util.accessData;


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

import com.util.AccessRead;

public class AccessImp implements AccessRead {
	
	private String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	private String dbUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};";
	private Connection conn = null;
	private Statement  stmt = null;
	private String filename = null;
	
	//表名
	private List<String> listName = new ArrayList<String>();
	//表头数据
	ArrayList<String> tableHeadList = new ArrayList<String>();
	//表数据
	ArrayList<String>  dataList = new ArrayList<String>();
	
	public void read(String dbName){
		
		init(dbName);
		try {
			getAllTableName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			getTableHeadName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//初始化 
    private void init(String dbName){
    	File file = new File(".");
    	String database = file.getAbsolutePath().replace(".", dbName);
    	dbUrl = dbUrl +"DBQ="+database;
    	filename = dbName;
    	listName.clear();
    	try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    	
    }
    
    public static void main(String args[]){
    	AccessImp access = new AccessImp();
    	access.read("xx.mdb");
    	
    }
    
    //获取所有表名
	private void getAllTableName() throws SQLException{
		try {
			conn = DriverManager.getConnection(dbUrl);
			stmt=conn.createStatement();    
			String sql4="SELECT   * FROM   MSysObjects WHERE   Flags=0   AND   Type=1";  //查询数据库中的所有表
      	    ResultSet rs=stmt.executeQuery(sql4); 
      	  while(rs.next()){
  			System.out.println(rs.getString("name"));
  			listName.add(rs.getString("name"));
      	  }  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			  stmt.close();    
			  conn.close();    
			  e.printStackTrace();
		} 
		
		stmt.close();    
        conn.close();    
	}
	
	//获取所有表头字段，根据表名
	private void getTableHeadName() throws SQLException, IOException{
		try {
			conn = DriverManager.getConnection(dbUrl);
			stmt=conn.createStatement();
			
			
		    int tableLoop = 0;
			for(tableLoop = 0; tableLoop < listName.size(); tableLoop++){
				tableHeadList.clear();
				String sql5="select * from "+ listName.get(tableLoop);
				 ResultSet rs=stmt.executeQuery(sql5);  
			    ResultSetMetaData metaDate   =   rs.getMetaData();   
			    int   number   =   metaDate.getColumnCount();   
			    String[]   column   =   new   String[number];   
			    for   (int   j   =   0;   j   <   column.length;   j++)   {   
			       column[j]   =   metaDate.getColumnName(j   +   1);  
			       tableHeadList.add(column[j].toString());
			       System.out.println(column[j]);
			    } 
			    //一行行读取数据
			    while(rs.next()){
			    	System.out.println();
			    	dataList.clear();
			    	for(int loop = 0; loop < column.length; loop++){
			    		System.out.print(" "+rs.getString(loop));
			    		dataList.add(rs.getString(loop));
			    	}
			    }
			    //打印表头记录
			 //   FileUtil.headInfoWrite(filename, listName.get(tableLoop), tableHeadList);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			stmt.close();    
		    conn.close();
		   // FileUtil.headInfoErr(filename);
			e.printStackTrace();
		}
		stmt.close();    
	    conn.close();
	}
	

}

