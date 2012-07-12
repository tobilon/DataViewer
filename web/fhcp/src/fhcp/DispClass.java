/**
*  Title  财务管理系统
*  @author: trowa
*  Company: http://www.upol.cn
*  Copyright: Copyright (c) 2004
*  @version 1.0
*  栏目表中的一些查询操作
*/
package fhcp;

import java.sql.*;
import java.util.*;

public class DispClass extends IClass{

public ResultSet rs;

public DispClass(){};

/*
* 得到所有栏目
*/
public Vector allClass() {
	DBConnect dbc = null;
	Vector allClassVector = new Vector();
	try{
		dbc  = new DBConnect();
		dbc.prepareStatement("SELECT * FROM class order by id");
		rs = dbc.executeQuery();
			while(rs.next()){
			IClass iclass = new IClass();
			iclass.setID(rs.getInt("id"));
			iclass.setName(rs.getString("name"));
			allClassVector.add(iclass);
		}
	}catch(Exception e){
		System.err.println(e);
	}finally{
		try{
			dbc.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	return allClassVector;
}

/*
* 根据ID得到栏目
*/
public IClass idToClass() {
	DBConnect dbc = null;
	IClass iclass = new IClass();
	try{
		dbc  = new DBConnect();
		dbc.prepareStatement("SELECT * FROM class WHERE id = ?");
		dbc.setInt(1,ID);
		rs = dbc.executeQuery();
			if(rs.next()){
			iclass.setID(rs.getInt("id"));
			iclass.setName(rs.getString("name"));
		}
	}catch(Exception e){
		System.err.println(e);
	}finally{
		try{
			dbc.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	return iclass;
}

/*
* 计算栏目的总数
*/
public int classNum() {
	DBConnect dbc = null;
	int classCount = 0;
	try{
		dbc  = new DBConnect();
		dbc.prepareStatement("SELECT count(*) FROM class");
		rs = dbc.executeQuery();
		if(rs.next())	classCount = rs.getInt(1);
	}catch(Exception e){
		System.err.println(e);
	}finally{
		try{
			dbc.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	return classCount;
}



}
