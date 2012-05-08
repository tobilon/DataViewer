package com.util.export;

import java.util.ArrayList;

public class Users {
	
	
	int id = 0;
	String name="";
	String address="";
	String company="";
	String idCard="";
	ArrayList<String> mobilePhonelist = new ArrayList<String>();
	String fixPhone="";
	String mail="";
	String born="";
	String sex="";
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getMobile1() {
		if(mobilePhonelist.size() >= 1){
		       this.mobile5 = mobilePhonelist.get(0);
			}
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		if(mobilePhonelist.size() >= 2){
		       this.mobile5 = mobilePhonelist.get(1);
			}
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		if(mobilePhonelist.size() >= 3){
		       this.mobile5 = mobilePhonelist.get(2);
			}
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getMobile4() {
		if(mobilePhonelist.size() >= 4){
		       this.mobile5 = mobilePhonelist.get(3);
			}
		return mobile4;
	}
	public void setMobile4(String mobile4) {
		this.mobile4 = mobile4;
	}
	public String getMobile5() {
		if(mobilePhonelist.size() >= 5){
		       this.mobile5 = mobilePhonelist.get(4);
			}
		return mobile5;
	}
	public void setMobile5(String mobile5) {
		this.mobile5 = mobile5;
	}
	int age;
	String post="";
	String mobile1="";
	String mobile2="";
	String mobile3="";
	String mobile4="";
	String mobile5="";
	String dataSource;
	
	public Users(){
		mobilePhonelist.clear();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public ArrayList<String> getMobilePhonelist() {
		return mobilePhonelist;
	}
	public void setMobilePhonelist(ArrayList<String> mobilePhonelist) {
		this.mobilePhonelist = mobilePhonelist;
	}
	public String getFixPhone() {
		return fixPhone;
	}
	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getBorn() {
		return born;
	}
	public void setBorn(String born) {
		this.born = born;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
	

}
