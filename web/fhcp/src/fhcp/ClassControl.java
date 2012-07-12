/**
*  Title  �������ϵͳ
*  @author: trowa
*  Company: http://www.upol.cn
*  Copyright: Copyright (c) 2004
*  @version 1.0
*  ��class���һЩ����ɾ���Ĳ���
*/
package fhcp;

import java.sql.*;
import java.util.*;

public class ClassControl extends IClass{

public ResultSet rs;

public ClassControl(){};

/*
* �޸���Ŀ����
*/
public void modifyclass(){
DBConnect dbc = null;
	try{
		dbc = new DBConnect();
		dbc.prepareStatement("UPDATE class SET name=? WHERE id=?");
		dbc.setBytes(1,name.getBytes("GB2312"));
		dbc.setInt(2,ID);
		dbc.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
	try{
		dbc.close();
	}catch(Exception e){
		e.printStackTrace();
		}
	}
}

/*
* ɾ����Ŀ
*/
public void delclass(){
	DBConnect dbc = null;
	try{
		dbc = new DBConnect();
		dbc.prepareStatement("delete from class WHERE id=?");
		dbc.setInt(1,ID);
		dbc.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
	try{
		dbc.close();
	}catch(Exception e){
		e.printStackTrace();
		}
	}
}

/*
* ����µ���Ŀ
*/
public void addclass(){
DBConnect dbc = null;
	try{
		dbc  = new DBConnect();
		dbc.prepareStatement("INSERT INTO class ( name ) VALUES ( ? )");
		dbc.setBytes(1,name.getBytes("GB2312"));
		dbc.executeUpdate();
	}catch(Exception e){
		System.err.println(e);
	}finally{
	try{
		dbc.close();
	}catch(Exception e){
		e.printStackTrace();
		}
	}
}





}
