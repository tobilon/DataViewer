/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  登录日志记录
 */

//管理登陆记录信息
package fhcp;

import java.sql.*;
import java.util.*;

public class LogControl extends Log {
	public ResultSet rs;

	public LogControl() {
	}

	/*
	 * 每次登陆的时候记录登陆用户名，登陆时间，登陆ip
	 */
	public void addlogin() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("INSERT INTO userlog (username,logindate,logoutdate,ip) VALUES ( ?,current_date,current_date,? )");
			dbc.setString(1, new String(user.getBytes("iso-8859-1"),"gb2312"));
			dbc.setString(2, new String(ip.getBytes("iso-8859-1"),"gb2312"));
			dbc.execute();
		} catch (Exception e) {
			System.err.println("LogCtrl.addlogin");
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 刷新离开的时间
	 */
	public void addlogoutdate() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("UPDATE userlog SET logoutdate=current_date WHERE id=?");
			dbc.setInt(1, id);
			dbc.execute();
		} catch (Exception e) {
			System.err.println("LogCtrl.addlogoutdate");
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
