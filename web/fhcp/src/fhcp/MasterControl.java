/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  ����Ա��Ϣά��
 */

package fhcp;

import java.sql.*;
import java.util.*;

public class MasterControl extends Master {

	public ResultSet rs;

	public MasterControl() {
	};

	/*
	 * ����Ա��½������һ
	 */
	public void addloginnum() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("UPDATE master SET loginnum = loginnum+1 WHERE name = ?");
			dbc.setString(1,new String(userName.getBytes("iso-8859-1"),"gb2312"));
			dbc.execute();
		} catch (Exception e) {
			System.err.println("MasterControl.addloginnum");
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
	 * ����µĹ���Ա
	 */
	public void addmaster() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("INSERT INTO master ( name,pass,joindate,loginnum,classid,truename,ipaddress ) VALUES ( ?,?,current_date,0,?,?,? )");
			dbc.setString(1, new String(userName.getBytes("iso-8859-1"),"gb2312"));
			dbc.setString(2, new String(userPassword.getBytes("iso-8859-1"),"gb2312"));
			dbc.setInt(3, classid);
			dbc.setString(4, new String(userTruename.getBytes("iso-8859-1"),"gb2312"));
			dbc.setString(5, new String(userIpaddress.getBytes("iso-8859-1"),"gb2312"));
			dbc.execute();
		} catch (Exception e) {
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
	 * ɾ������Ա
	 */
	public void delmaster() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("delete from master WHERE id=?");
			dbc.setInt(1, ID);
			dbc.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * �޸Ĺ���Ա��Ϣ
	 */
	public void modifymaster() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("UPDATE master SET name=?,pass=?,classid=?,joindate=?,loginnum=?,truename=?,ipaddress=? WHERE id=?");
			dbc.setString(1, new String(userName.getBytes("iso-8859-1"),"gb2312"));
			dbc.setString(2, new String(userPassword.getBytes("iso-8859-1"),"gb2312"));
			dbc.setInt(3, classid);
			dbc.setString(4, new String(joindate.getBytes("iso-8859-1"),"gb2312"));
			dbc.setInt(5, loginnum);
			dbc.setString(6, new String(userTruename.getBytes("iso-8859-1"),"gb2312"));
			dbc.setString(7, new String(userIpaddress.getBytes("iso-8859-1"),"gb2312"));
			dbc.setInt(8, ID);
			dbc.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * �û��޸�����
	 */
	public void modifymasteruser() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("UPDATE master SET pass=? WHERE id=?");
			dbc.setString(1, new String(userPassword.getBytes("iso-8859-1"),"gb2312"));
			dbc.setInt(2, ID);
			dbc.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
