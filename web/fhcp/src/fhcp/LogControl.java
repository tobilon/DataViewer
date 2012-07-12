/**
*  Title  财务管理系统
*  @author: trowa
*  Company: http://www.upol.cn
*  Copyright: Copyright (c) 2004
*  @version 1.0
*  登陆信息的一些增，删，改操作
*/

//管理登陆记录信息
package fhcp;

import java.sql.*;
import java.util.*;

public class LogControl extends Log{
    public ResultSet rs;
    public LogControl() {}

/*
* 每次登陆的时候记录登陆用户名，登陆时间，登陆ip
*/
public void addlogin(){
DBConnect dbc = null;
        try{
                dbc  = new DBConnect();
                dbc.prepareStatement("INSERT INTO userlog ( user,logindate,logoutdate,ip ) VALUES ( ?,?,?,? )");
                dbc.setBytes(1,user.getBytes("GB2312"));
                dbc.setBytes(2,GetDate.getStringDate().getBytes("GB2312"));
                dbc.setBytes(3,GetDate.getStringDate().getBytes("GB2312"));
                dbc.setBytes(4,ip.getBytes("GB2312"));
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
* 刷新离开的时间
*/
public void addlogoutdate(){
DBConnect dbc = null;
        try{
                dbc  = new DBConnect();
                dbc.prepareStatement("UPDATE userlog SET logoutdate=? WHERE id=?");
                dbc.setBytes(1,GetDate.getStringDate().getBytes("GB2312"));
                dbc.setInt(2,id);
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
