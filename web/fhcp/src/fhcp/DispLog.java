/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  ���ݿ��¼��־��¼
 */

//��ʾ��½��Ϣ
package fhcp;

import java.sql.*;
import java.util.*;

public class DispLog extends Log {
	public ResultSet rs;

	public DispLog() {
	}

	/*
	 * ��������û��ĵ�½��Ϣ
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
	 * ��õ�½��Ϣ��id��
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
	 * ��õ�½��Ϣ���ܼ�¼��
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
