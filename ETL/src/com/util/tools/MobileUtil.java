package com.util.tools;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class MobileUtil {

	static String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	static String dbUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};";
	static Connection conn = null;
	static PreparedStatement  stmt = null;
	static String sqlStatement = "select City,Memo from T_mobile where Code = ?";
	static HashMap map = new HashMap();
	
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
				conn = DriverManager.getConnection(dbUrl);
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
					map.put("memo", res.getString(2));

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
