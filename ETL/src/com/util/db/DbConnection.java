package com.util.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.util.tools.Log;


public class DbConnection {

	private static Connection conn = null;
	private static Properties props = null;

	static {
		props = new Properties();
		File file = new File(".");
		String path = file.getAbsoluteFile()+"\\config\\dbconf.properties";
		System.out.println(path);
		try {
			props.load(new FileInputStream(path));
		} catch (IOException e1) {
			Log.error("props load failed");
			e1.printStackTrace();
		}
		try {
			Class.forName(props.getProperty("driverClass"));
			Log.error("Class.forName failed");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	public static Connection getConn(){
		try {
			if(conn == null || conn.isClosed())
			{
				conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
			}
			//conn.setAutoCommit(false);
		} catch (SQLException e) {
			Log.error("get conn failed");
			e.printStackTrace();
		}
		return conn;
	}

	
	
	public static void closeConn(){
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}