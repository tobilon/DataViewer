/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  操作员数据结构
 */

package fhcp;

public class Master {

	public String userName, userPassword, joindate, userTruename,
			userIpaddress;

	public int ID, classid, loginnum;

	public Master() {
	};

	public int getID() {
		return ID;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getJoindate() {
		return joindate;
	}

	public int getClassid() {
		return classid;
	}

	public int getLoginnum() {
		return loginnum;
	}

	public String getUserTruename() {
		return userTruename;
	}

	public String getUserIpaddress() {
		return userIpaddress;
	}

	// 取管理员的ID
	public void setID(int i) {
		this.ID = i;
	}

	// 取管理员的用户名
	public void setUserName(String s) {
		this.userName = s;
	}

	// 取管理员的密码
	public void setUserPassword(String s) {
		this.userPassword = s;
	}

	// 取管理员的加入时间
	public void setJoindate(String s) {
		this.joindate = s;
	}

	// 取管理员所在的栏目
	public void setClassid(int i) {
		this.classid = i;
	}

	// 取管理员的登陆次数
	public void setLoginnum(int i) {
		this.loginnum = i;
	}

	// 取管理员的真实姓名
	public void setUserTruename(String s) {
		this.userTruename = s;
	}

	// 取管理员的限制IP
	public void setUserIpaddress(String s) {
		this.userIpaddress = s;
	}

}
