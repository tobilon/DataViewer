/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  ����Ա��Ϣ��ʾ
 */

package fhcp;

import java.sql.*;
import java.util.*;

public class DispMaster extends Master {

	public boolean ismaster;

	public ResultSet rs;

	public DispMaster() {
	};

	/* login */
	public boolean chkLogin() throws Exception {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM master WHERE name = ? and pass = ?");
			dbc.setString(1, userName);
			dbc.setString(2, userPassword);
			rs = dbc.executeQuery();
			if (!rs.next()) {
				ismaster = false;
			} else {
				ismaster = true;
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ismaster;
	}

	/*
	 * ��ѯ���еĹ���Ա,����Ƿ��ظ�
	 */
	public boolean reLogin() throws Exception {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM master WHERE name = ?");
			dbc.setString(1, new String(userName.getBytes("iso-8859-1"),"gb2312"));
			rs = dbc.executeQuery();
			if (!rs.next()) {
				ismaster = false;
			} else {
				ismaster = true;
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ismaster;
	}

	/*
	 * ��ѯ���еĹ���Ա(��id����)
	 */
	public Vector allMaster() {
		DBConnect dbc = null;
		Vector allMasterVector = new Vector();

		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM master order by id desc");
			rs = dbc.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setID(rs.getInt("id"));
				master.setUserName(rs.getString("name"));
				master.setUserPassword(rs.getString("pass"));
				master.setJoindate(rs.getString("joindate"));
				master.setClassid(rs.getInt("classid"));
				master.setLoginnum(rs.getInt("loginnum"));
				allMasterVector.add(master);
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
		return allMasterVector;
	}

	/*
	 * ��ѯ���еĹ���Ա(��id����)
	 */
	public Vector masterOrderID() {
		DBConnect dbc = null;
		Vector allMasterVector = new Vector();

		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM master order by id");
			rs = dbc.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setID(rs.getInt("id"));
				master.setUserName(rs.getString("name"));
				master.setUserPassword(rs.getString("pass"));
				master.setJoindate(rs.getString("joindate"));
				master.setClassid(rs.getInt("classid"));
				master.setLoginnum(rs.getInt("loginnum"));
				master.setUserTruename(rs.getString("truename"));
				master.setUserIpaddress(rs.getString("ipaddress"));
				allMasterVector.add(master);
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
		return allMasterVector;
	}

	/*
	 * �������Ա������
	 */
	public int masterNum() {
		DBConnect dbc = null;
		int masterCount = 0;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT count(*) FROM master");
			rs = dbc.executeQuery();
			if (rs.next())
				masterCount = rs.getInt(1);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return masterCount;
	}

	/*
	 * ����ID�õ�����Ա
	 */
	public Master idToMaster() {
		DBConnect dbc = null;
		Master master = new Master();
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM master WHERE id = ?");
			dbc.setInt(1, ID);
			rs = dbc.executeQuery();
			if (rs.next()) {
				master.setID(rs.getInt("id"));
				master.setUserName(rs.getString("name"));
				master.setUserPassword(rs.getString("pass"));
				master.setJoindate(rs.getString("joindate"));
				master.setClassid(rs.getInt("classid"));
				master.setLoginnum(rs.getInt("loginnum"));
				master.setUserTruename(rs.getString("truename"));
				master.setUserIpaddress(rs.getString("ipaddress"));
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return master;
	}

	/*
	 * �����û����Ƶõ�����Ա
	 */
	public Master nameToMaster() {
		DBConnect dbc = null;
		Master master = new Master();
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM master WHERE name = ?");
			dbc.setString(1, new String(userName.getBytes("iso-8859-1"),"gb2312"));
			rs = dbc.executeQuery();
			if (rs.next()) {
				master.setID(rs.getInt("id"));
				master.setUserName(rs.getString("name"));
				master.setUserPassword(rs.getString("pass"));
				master.setJoindate(rs.getString("joindate"));
				master.setClassid(rs.getInt("classid"));
				master.setLoginnum(rs.getInt("loginnum"));
				master.setUserTruename(rs.getString("truename"));
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return master;
	}

}
