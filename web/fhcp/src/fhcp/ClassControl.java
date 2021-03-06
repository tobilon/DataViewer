/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  对class表的一些增，删，改操作
 */
package fhcp;

import java.sql.*;
import java.util.*;

public class ClassControl extends IClass {

	public ResultSet rs;

	public ClassControl() {
	};

	/*
	 * 修改栏目名称
	 */
	public void modifyclass() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("UPDATE class SET name=? WHERE id=?");
			dbc.setString(1, new String(name.getBytes("iso-8859-1"), "gb2312"));
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

	/*
	 * 删除栏目
	 */
	public void delclass() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("delete from class WHERE id=?");
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
	 * 添加新的栏目
	 */
	public void addclass() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("INSERT INTO class ( name ) VALUES ( ? )");
			dbc.setString(1, new String(name.getBytes("iso-8859-1"), "gb2312"));
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
}
