/**
*  Title  �������ϵͳ
*  @author: trowa
*  Company: http://www.upol.cn
*  Copyright: Copyright (c) 2004
*  @version 1.0
*  ��Ŀ������ʹ�õ�һЩ�ֶ�
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


	//ȡ������ĿID
	public void setID(int i){
		this.ID = i;
	}

	//ȡ������Ŀ��
	public void setName(String s){
		this.name = s;
	}



}

