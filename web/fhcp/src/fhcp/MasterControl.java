/**
*  Title  �������ϵͳ
*  @author: trowa
*  Company: http://www.upol.cn
*  Copyright: Copyright (c) 2004
*  @version 1.0
*  ����Ա��һЩ����ɾ���Ĳ���
*/
package fhcp;

import java.sql.*;
import java.util.*;

public class MasterControl extends Master{

public ResultSet rs;

public MasterControl(){};



/*
* ����Ա��½������һ
*/
public void addloginnum(){
DBConnect dbc = null;
	try{
		dbc  = new DBConnect();
		dbc.prepareStatement("UPDATE master SET loginnum = loginnum+1 WHERE name = ?");
		dbc.setBytes(1,userName.getBytes("GB2312"));
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

/*
* ����µĹ���Ա
*/
public void addmaster(){
DBConnect dbc = null;
	try{
		dbc  = new DBConnect();
		dbc.prepareStatement("INSERT INTO master ( name,pass,joindate,classid,truename,ipaddress ) VALUES ( ?,?,?,?,?,? )");
		dbc.setBytes(1,userName.getBytes("GB2312"));
		dbc.setBytes(2,userPassword.getBytes("GB2312"));
		dbc.setBytes(3,GetDate.getStringDateShort().getBytes("GB2312"));
		dbc.setInt(4,classid);
                dbc.setBytes(5,userTruename.getBytes("GB2312"));
                dbc.setBytes(6,userIpaddress.getBytes("GB2312"));
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

/*
* ɾ������Ա
*/
public void delmaster(){
	DBConnect dbc = null;
	try{
		dbc = new DBConnect();
		dbc.prepareStatement("delete from master WHERE id=?");
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
* �޸Ĺ���Ա��Ϣ
*/
public void modifymaster(){
DBConnect dbc = null;
	try{
		dbc = new DBConnect();
		dbc.prepareStatement("UPDATE master SET name=?,pass=?,classid=?,joindate=?,loginnum=?,truename=?,ipaddress=? WHERE id=?");
                dbc.setBytes(1,userName.getBytes("GB2312"));
		dbc.setBytes(2,userPassword.getBytes("GB2312"));
		dbc.setInt(3,classid);
		dbc.setBytes(4,joindate.getBytes("GB2312"));
		dbc.setInt(5,loginnum);
		dbc.setBytes(6,userTruename.getBytes("GB2312"));
                dbc.setBytes(7,userIpaddress.getBytes("GB2312"));
                dbc.setInt(8,ID);
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
    * �û��޸�����
    */
    public void modifymasteruser(){
    DBConnect dbc = null;
            try{
                    dbc = new DBConnect();
                    dbc.prepareStatement("UPDATE master SET pass=? WHERE id=?");
                    dbc.setBytes(1,userPassword.getBytes("GB2312"));
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



}
