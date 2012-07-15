/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  数据库登录日志记录
 */

//显示登陆信息
package fhcp;

import java.sql.*;
import java.util.*;

public class DispLog extends Log {
	public ResultSet rs;

	public DispLog() {
	}

	/*
	 * 获得所有用户的登陆信息
	 */
	public Vector allLog() {
		DBConnect dbc = null;
		Vector allLogVector = new Vector();

		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM userlog order by id desc");
			rs = dbc.executeQuery();
			while (rs.next()) {
				Log log = new Log();
				log.setId(rs.getInt("id"));
				log.setUser(rs.getString("username"));
				log.setLogindate(rs.getString("logindate"));
				log.setLogoutdate(rs.getString("logoutdate"));
				log.setIp(rs.getString("ip"));
				allLogVector.add(log);
			}
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allLogVector;
	}

	/*
	 * 获得登陆信息的id号
	 */
	public int getlogid() {
		int logid = 0;
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM userlog where username=?");
			dbc.setString(1, user);
			rs = dbc.executeQuery();
			while (rs.next()) {
				logid = rs.getInt("id");

			}
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return logid;
	}

	/*
	 * 获得登陆信息的总记录数
	 */
	public int lognum() {
		DBConnect dbc = null;
		int logcount = 0;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT count(*) FROM userlog");
			rs = dbc.executeQuery();
			if (rs.next())
				logcount = rs.getInt(1);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return logcount;
	}

}
