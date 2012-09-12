/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  短信业务数据结构
 */

package fhcp;

public class Service {
	public String svrContent, svrDate, svrTime;

    public int ID;
    
    public Service() {
	};
    
	// 获取短信ID
	public int getID() {
		return ID;
	}

	// 获取短信内容
	public String getSvrContent() {
		return svrContent;
	}

	// 获取短信发送日期
	public String getSvrDate() {
		return svrDate;
	}

	// 获取短信发送时间
	public String getSvrTime() {
		return svrTime;
	}

	// 设置短信ID
	public void setID(int i) {
		this.ID = i;
	}

	// 设置短信内容
	public void setSvrContent(String s) {
		this.svrContent = s;
	}

	// 设置短信发送日期
	public void setSvrDate(String s) {
		this.svrDate = s;
	}

	// 设置短信发送时间
	public void setSvrTime(String s) {
		this.svrTime = s;
	}	
}
