/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  ����Ա�������ݽṹ
 */

package fhcp;

import java.*;

public class IClass {

	public String name;

	public int ID;

	public IClass() {
	};

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	// ȡ������ĿID
	public void setID(int i) {
		this.ID = i;
	}

	// ȡ������Ŀ��
	public void setName(String s) {
		this.name = s;
	}

}
