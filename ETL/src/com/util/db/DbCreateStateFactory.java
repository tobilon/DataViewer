package com.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbCreateStateFactory {

	private static PreparedStatement type_query_state = null;
	private static PreparedStatement  type_insert_state = null;
	
	public static PreparedStatement createStateInstance(String sqlType){
		if(sqlType.equals("typeQuery")){
			return createTypeQuery();
		}
		else if(sqlType.equals("typeInsert")){
			return createTypeInsert();
		}
		return null;
	}
	
	
	private static PreparedStatement createTypeQuery(){
		String typeQuerySql = "select * from customer_type";
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != type_query_state){
			return type_query_state;
		}
		try {
			type_query_state = conn.prepareStatement(typeQuerySql);
			return type_query_state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static PreparedStatement createTypeInsert(){
		String typeInsertSql = "insert into customer_type(disc,tag) values(?,?)";
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != type_insert_state){
			return type_insert_state;
		}
		try {
			type_insert_state = conn.prepareStatement(typeInsertSql);
			return type_insert_state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
