package com.util.tools;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.util.db.DbConnection;

public class MobileUtil {

	static String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	static String dbUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};";
	static Connection conn = null;
	static PreparedStatement  stmt = null;
	static String sqlStatement = "select \"city\" from \"T_mobile\" where \"code\" = ?";
	static HashMap map = new HashMap();
	static LRUCache<String,String> cache = new LRUCache<String,String>();
	
	static {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			File file = new File(".");
			dbUrl += "DBQ=" +file.getAbsolutePath()+"\\config\\mobile.mdb";
			System.out.println(dbUrl);
			//conn = DriverManager.getConnection(dbUrl);;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void  getStatement(){
		if(conn == null){
			try {
				conn = DbConnection.getConn();
				stmt = conn.prepareStatement(sqlStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//return stmt;
	}
	
	public static HashMap getPhoneInfo(String phone){
		map.clear();
		getStatement();
		if(phone.length() != 7){
			System.out.println("null");
			//return null;
		}
		if(cache.size() > 0){
			String address = cache.get(phone);
			if(address != null){
				map.put("city", address);
				return map;
			}
		}
		else{
			try {
				if(stmt != null)
				System.out.println(phone);
				
				stmt.setString(1, phone);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ResultSet res = stmt.executeQuery();
				if(res.next()){
					map.put("city", res.getString(1));
					cache.put(phone, (String)map.get("city"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return map;		
		
	}
	
	
	public static void main(String[] args){
		
		HashMap map = getPhoneInfo("1358408");
		
		System.out.println(map.get("city")+ " "+map.get("memo"));
		
		
         map = getPhoneInfo("1860175");
		
		System.out.println(map.get("city")+ " "+map.get("memo"));
	}

}
