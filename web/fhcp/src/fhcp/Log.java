/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  ������־���ݽṹ
 */

//��½��Ϣ����
package fhcp;

public class Log {
	public int id;
	public String user;
	public String logindate, logoutdate, ip;

	// �������
	public int getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getLogindate() {
		return logindate;
	}

	public String getLogoutdate() {
		return logoutdate;
	}

	public String getIp() {
		return ip;
	}

	// ��������
	public void setId(int i) {
		this.id = i;// id��
	}

	public void setUser(String s) {
		this.user = s;// �û���
	}

	public void setLogindate(String s) {
		this.logindate = s;// ��½ʱ��
	}

	public void setLogoutdate(String s) {
		this.logoutdate = s;// �˳�ʱ��
	}

	public void setIp(String s) {
		this.ip = s;// ��½��ip��ַ
	}

	public Log() {
	};
}
