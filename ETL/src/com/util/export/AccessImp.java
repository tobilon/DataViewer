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


public class AccessImp implements AccessRead {
	
	private String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	private String dbUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};";
	private Connection conn = null;
	private Statement  stmt = null;
	private String filename = null;
	
	//����
	private List<String> listName = new ArrayList<String>();
	//��ͷ����
	ArrayList<String> tableHeadList = new ArrayList<String>();
	//������
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
	
	//��ʼ�� 
    private void init(String dbName){
    	File file = new File(".");
    	String database = file.getAbsolutePath().replace(".", dbName);
    	dbUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};";
    	dbUrl = dbUrl +"DBQ="+database;
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
    
    public static void main(String args[]){
    	AccessImp access = new AccessImp();
    	access.read("mobile.mdb");
    	
    }
    
    //��ȡ���б���
	private void getAllTableName() throws SQLException{
		try {
			conn = DriverManager.getConnection(dbUrl);
			stmt=conn.createStatement();    
			DatabaseMetaData  dbmd=conn.getMetaData();  
			ResultSet  rs=dbmd.getTables(null,null,"%",null); 
			String tablename = null;
			while(rs.next()){  
				tablename = rs.getString(3);
				System.out.println(tablename);
				if(!tablename.startsWith("MSys"))
			     listName.add(tablename);
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
	
	//��ȡ���б�ͷ�ֶΣ����ݱ���
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
			       System.out.println("xxxx :"+column[j].toString());
			    } 
			    //һ���ж�ȡ����
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
			    	UsersExport.anasys(filename, dataList);
			    }
			    //��ӡ��ͷ��¼
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

