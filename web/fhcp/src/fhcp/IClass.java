/**
*  Title  财务管理系统
*  @author: trowa
*  Company: http://www.upol.cn
*  Copyright: Copyright (c) 2004
*  @version 1.0
*  栏目表中所使用的一些字段
*/
package fhcp;

import java.*;


public class IClass {

	public String name;

	public int ID;

	public IClass(){};

	public int getID(){
		return ID;
	}

	public String getName(){
		return name;
	}


	//取新闻栏目ID
	public void setID(int i){
		this.ID = i;
	}

	//取新闻栏目名
	public void setName(String s){
		this.name = s;
	}



}

