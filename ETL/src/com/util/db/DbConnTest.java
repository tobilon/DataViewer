package com.util.db;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.util.tools.Log;


public class DbConnTest {
   public static boolean testConn(String driver,String url,String user,String passwd) {
	   try {
		Class.forName(driver);
		  
		Connection conn = null;
		//System.out.println(driver+url+user);
		try {
			//System.out.println("2222222222");
			conn = DriverManager.getConnection(url,user,passwd);
			//System.out.println("3333333");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		
		e.printStackTrace();
		return false;
	}
	
   }
   
   
   public static boolean dbtest(){
	   Properties props = new Properties();
	   File file = new File(".");
	   String path = file.getAbsoluteFile()+"\\config\\dbconf.properties";
	   System.out.println(path);
	   
		try {
			props.load(new FileInputStream(path));
		} catch (IOException e1) {
			System.out.println("ddddd");
			Log.error("props load failed");
			e1.printStackTrace();
			return false;
		}
		
		return testConn(props.getProperty("driverClass"),props.getProperty("url"),props.getProperty("username"),props.getProperty("password"));
		
   }
}
