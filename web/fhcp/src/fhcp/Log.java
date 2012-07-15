/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  操作日志数据结构
 */

//登陆信息的类
package fhcp;

public class Log {
	public int id;
	public String user;
	public String logindate, logoutdate, ip;

	// 获得数据
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

	// 设置数据
	public void setId(int i) {
		this.id = i;// id号
	}

	public void setUser(String s) {
		this.user = s;// 用户名
	}

	public void setLogindate(String s) {
		this.logindate = s;// 登陆时间
	}

	public void setLogoutdate(String s) {
		this.logoutdate = s;// 退出时间
	}

	public void setIp(String s) {
		this.ip = s;// 登陆人ip地址
	}

	public Log() {
	};
}
