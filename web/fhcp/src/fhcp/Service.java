/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  ����ҵ�����ݽṹ
 */

package fhcp;

public class Service {
	public String svrContent, svrDate, svrTime;

    public int ID;
    
    public Service() {
	};
    
	// ��ȡ����ID
	public int getID() {
		return ID;
	}

	// ��ȡ��������
	public String getSvrContent() {
		return svrContent;
	}

	// ��ȡ���ŷ�������
	public String getSvrDate() {
		return svrDate;
	}

	// ��ȡ���ŷ���ʱ��
	public String getSvrTime() {
		return svrTime;
	}

	// ���ö���ID
	public void setID(int i) {
		this.ID = i;
	}

	// ���ö�������
	public void setSvrContent(String s) {
		this.svrContent = s;
	}

	// ���ö��ŷ�������
	public void setSvrDate(String s) {
		this.svrDate = s;
	}

	// ���ö��ŷ���ʱ��
	public void setSvrTime(String s) {
		this.svrTime = s;
	}	
}
