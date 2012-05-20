package com.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbCreateStateFactory {

	private static PreparedStatement type_query_state = null;
	private static PreparedStatement type_insert_state = null;
	private static PreparedStatement mail_query_state = null; 
	private static PreparedStatement mail_insert_state = null;
	private static PreparedStatement mail_delete_state = null;
	private static PreparedStatement phone_query_state = null;
	private static PreparedStatement phone_insert_state = null;
	private static PreparedStatement phone_delete_state = null;
	private static PreparedStatement profile_insert_state = null;
	private static PreparedStatement profile_update_state = null;
	private static PreparedStatement profile_query_state = null;
	private static PreparedStatement profile_delete_state = null;
	private static PreparedStatement report_insert_state = null;
	private static PreparedStatement report_query_state = null;
	private static PreparedStatement report_update_state = null;
	private static PreparedStatement report_delete_state = null;
	private static PreparedStatement report_maxid_state = null;
	
	public static PreparedStatement createStateInstance(String sqlType){
		if(sqlType.equals("typeQuery")){
			return createTypeQuery();
		}
		else if(sqlType.equals("typeInsert")){
			return createTypeInsert();
		}
		
		/* create mail table SQL statement */
		
		/* mailQuery: query user id by e-mail */
		else if(sqlType.equals("mailQuery")){
			return createMailQuery();
		}
		
		/* mailInsert: insert user-id & mail values*/
		else if(sqlType.equals("mailInsert")){
			return createMailInsert();
		}
		
		/* mailDelete: delete user-id & mail values */
		else if(sqlType.equals("mailDelete")){
			return createMailDelete();
		}
		
		/* create phone table SQL statement */
		
		/* phoneQuery: query user id by phone */
		else if(sqlType.equals("phoneQuery")){
			return createPhoneQuery();
		}
		
		/* phoneInsert: insert user id & mobile phone */
		else if(sqlType.equals("phoneInsert")){
			return createPhoneInsert();
		}
		
		/* phoneDelete: delete user id & mobile phone */
		else if(sqlType.equals("phoneDelete")){
			return createPhoneDelete();
		}
		
		/* create profile table SQL statement */
			
		/* profileInsert: insert user profile */
		else if(sqlType.equals("profileInsert")){
			return createProfileInsert();
		}
		
		/* profileUpdate: update user profile */
		else if(sqlType.equals("profileUpdate")){
			return createProfileUpdate();
		}
		
		/* profileQuery: query user profile */
		else if(sqlType.equals("profileQuery")){
			return createProfileQuery();
		}
		
		/* profileDelete: delete user profile */
		else if(sqlType.equals("profileDelete")){
			return createProfileDelete();			
		}
		
		/* create report table SQL statement */
		
		/* reportInsert: insert ETL report */
		else if(sqlType.equals("reportInsert")){
			return createReportInsert();
		}
		
		/* profileUpdate: update ETL report */
		else if(sqlType.equals("reportUpdate")){
			return createReportUpdate();
		}
		
		/* profileQuery: query ETL report */
		else if(sqlType.equals("reportQuery")){
			return createReportQuery();
		}
		
		/* profileDelete: delete ETL report */
		else if(sqlType.equals("reportDelete")){
			return createReportDelete();			
		}
		
		/* profileMaxID: get max file id */
		else if(sqlType.equals("reportMaxID")){
			return createReportMaxID();
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
	
	private static PreparedStatement createMailQuery()
	{
		String typeQuerySql = "select userid from customer_mail where mail=?";
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != mail_query_state){
			return mail_query_state;
		}
		try {
			mail_query_state = conn.prepareStatement(typeQuerySql);
			return mail_query_state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static PreparedStatement createMailInsert()
	{
		String typeQuerySql = "insert into customer_mail(userid,mail) values (?,?)";
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != mail_insert_state){
			return mail_insert_state;
		}
		try {
			mail_insert_state = conn.prepareStatement(typeQuerySql);
			return mail_insert_state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static PreparedStatement createMailDelete()
	{
		String typeQuerySql = "delete from customer_mail where userid=?";
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != mail_delete_state){
			return mail_delete_state;
		}
		try {
			mail_delete_state = conn.prepareStatement(typeQuerySql);
			return mail_delete_state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static PreparedStatement createPhoneQuery()
	{
		String typeQuerySql = "select userid from customer_phone where MobilePhone=?";
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != phone_query_state){
			return phone_query_state;
		}
		try {
			phone_query_state = conn.prepareStatement(typeQuerySql);
			return phone_query_state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static PreparedStatement createPhoneInsert()
	{
		String typeQuerySql = "insert into customer_phone(userid, MobilePhone) values (?,?)";
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != phone_insert_state){
			return phone_insert_state;
		}
		try {
			phone_insert_state = conn.prepareStatement(typeQuerySql);
			return phone_insert_state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static PreparedStatement createPhoneDelete()
	{
		String typeQuerySql = "delete from customer_phone where userid=?";
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != phone_delete_state){
			return phone_delete_state;
		}
		try {
			phone_delete_state = conn.prepareStatement(typeQuerySql);
			return phone_delete_state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static PreparedStatement createProfileInsert()
	{
		String typeQuerySql = "insert into customer_profile(name, address, " +
				"company, province, city, born, idcard, MobilePhone1," +
				"MobilePhone2, MobilePhone3, MobilePhone4, HomePhone1," +
				"HomePhone2, post, mail, sex, UserType, source, extra) values " +
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != profile_insert_state){
			return profile_insert_state;
		}
		try {
			profile_insert_state = conn.prepareStatement(typeQuerySql);
			return profile_insert_state;
		} catch (SQLException e) {
		
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	private static PreparedStatement createProfileUpdate()
	{
		String typeQuerySql = "update customer_profile set name=?,address=?,"+
		         "company=?,province=?,city=?,born=?,idcard=?,MobilePhone1=?,"+
		         "MobilePhone2=?, MobilePhone3=?, MobilePhone4=?, HomePhone1=?,"+
		         "HomePhone2=?,post=?,mail=?,sex=?,UserType=?,source=?,extra=? "+
		         "where id=?";
		
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != profile_update_state){
			return profile_update_state;
		}
		try {
			profile_update_state = conn.prepareStatement(typeQuerySql);
			return profile_update_state;
		} catch (SQLException e) {
		
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	private static PreparedStatement createProfileQuery()
	{
		String typeQuerySql = "select * from customer_profile where id = ?";
		
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != profile_query_state){
			return profile_query_state;
		}
		try {
			profile_query_state = conn.prepareStatement(typeQuerySql);
			return profile_query_state;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;	
	}
	
	private static PreparedStatement createProfileDelete()
	{
		String typeQuerySql = "delete from customer_profile where source = ?";
		
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != profile_delete_state){
			return profile_delete_state;
		}
		try {
			profile_delete_state = conn.prepareStatement(typeQuerySql);
			return profile_delete_state;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;	
	}
	
	private static PreparedStatement createReportInsert()
	{
		String typeQuerySql = "insert into customer_report(filename, " +
				"usertype, usernum, importnum, timestamp) values " +
				"(?, ?, ?, ?, sysdate)";
		
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != report_insert_state){
			return report_insert_state;
		}
		try {
			report_insert_state = conn.prepareStatement(typeQuerySql);
			return report_insert_state;
		} catch (SQLException e) {
		
			
			e.printStackTrace();
		}
		return null;		
	}
	
	private static PreparedStatement createReportUpdate()
	{
		String typeQuerySql = "update customer_report setfilename=?, " +
				"usertype=?, usernum=?, importnum=?, timestamp=sysdate where id = ?";
		
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != report_update_state){
			return report_update_state;
		}
		try {
			report_update_state = conn.prepareStatement(typeQuerySql);
			return report_update_state;
		} catch (SQLException e) {
		
			
			e.printStackTrace();
		}
		return null;		
	}
	
	private static PreparedStatement createReportQuery()
	{
		String typeQuerySql = "select * from customer_report where id=?";
		
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != report_query_state){
			return report_query_state;
		}
		try {
			report_query_state = conn.prepareStatement(typeQuerySql);
			return report_query_state;
		} catch (SQLException e) {
		
			
			e.printStackTrace();
		}
		return null;		
	}
	
	private static PreparedStatement createReportDelete()
	{
		String typeQuerySql = "delete from customer_report where id=?";
		
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != report_delete_state){
			return report_delete_state;
		}
		try {
			report_delete_state = conn.prepareStatement(typeQuerySql);
			return report_delete_state;
		} catch (SQLException e) {
		
			
			e.printStackTrace();
		}
		return null;
	}
	
	private static PreparedStatement createReportMaxID()
	{
		String typeQuerySql = "select customer_report_seq.currval from dual";
		
		Connection conn = DbConnection.getConn();
		/* 避免重复创建*/
		if(null != report_maxid_state){
			return report_maxid_state;
		}
		try {
			report_maxid_state = conn.prepareStatement(typeQuerySql);
			return report_maxid_state;
		} catch (SQLException e) {
		
			
			e.printStackTrace();
		}
		return null;		
	}
}
