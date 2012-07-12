/**
*  Title  �������ϵͳ
*  @author: trowa
*  Company: http://www.upol.cn
*  Copyright: Copyright (c) 2004
*  @version 1.0
*  ��Ŀ���е�һЩ��ѯ����
*/
package fhcp;

import java.sql.*;
import java.util.*;

public class DispClass extends IClass{

public ResultSet rs;

public DispClass(){};

/*
* �õ�������Ŀ
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
* ����ID�õ���Ŀ
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
* ������Ŀ������
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
