/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  ����Ա���ݽṹ
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

	// ȡ����Ա��ID
	public void setID(int i) {
		this.ID = i;
	}

	// ȡ����Ա���û���
	public void setUserName(String s) {
		this.userName = s;
	}

	// ȡ����Ա������
	public void setUserPassword(String s) {
		this.userPassword = s;
	}

	// ȡ����Ա�ļ���ʱ��
	public void setJoindate(String s) {
		this.joindate = s;
	}

	// ȡ����Ա���ڵ���Ŀ
	public void setClassid(int i) {
		this.classid = i;
	}

	// ȡ����Ա�ĵ�½����
	public void setLoginnum(int i) {
		this.loginnum = i;
	}

	// ȡ����Ա����ʵ����
	public void setUserTruename(String s) {
		this.userTruename = s;
	}

	// ȡ����Ա������IP
	public void setUserIpaddress(String s) {
		this.userIpaddress = s;
	}

}
