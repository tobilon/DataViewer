/**
*  Title  �������ϵͳ
*  @author: trowa
*  Company: http://www.upol.cn
*  Copyright: Copyright (c) 2004
*  @version 1.0
*  ��½��Ϣ��һЩ����ɾ���Ĳ���
*/

//�����½��¼��Ϣ
package fhcp;

import java.sql.*;
import java.util.*;

public class LogControl extends Log{
    public ResultSet rs;
    public LogControl() {}

/*
* ÿ�ε�½��ʱ���¼��½�û�������½ʱ�䣬��½ip
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
* ˢ���뿪��ʱ��
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
