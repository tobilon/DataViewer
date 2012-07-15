/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  操作员部门数据结构
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

	// 取新闻栏目ID
	public void setID(int i) {
		this.ID = i;
	}

	// 取新闻栏目名
	public void setName(String s) {
		this.name = s;
	}

}
