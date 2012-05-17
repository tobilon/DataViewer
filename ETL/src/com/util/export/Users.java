package com.util.export;

import java.util.ArrayList;

public class Users {
	
	
	int id = 0;
	String name="";
	String address="";
	String company="";
	String idCard="";
	ArrayList<String> mobilePhonelist = new ArrayList<String>();
	ArrayList<String> fixPhoneList = new ArrayList<String>();
	//String fixPhone="";
	String mail="";
	String born="";
	String sex="";
	
	String post="";
	String mobile1="";
	String mobile2="";
	String mobile3="";
	String mobile4="";
	
	String fixPhone1 ="";
	String fixPhone2="";
	String province="";
	String city="";
	String usertype="";
	String remarks ="";
	
	String dataSource;
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getMobile1() {
		if(mobilePhonelist.size() >= 1){
		       this.mobile1 = mobilePhonelist.get(0);
			}
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		if(mobilePhonelist.size() >= 2){
		       this.mobile2 = mobilePhonelist.get(1);
			}
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		if(mobilePhonelist.size() >= 3){
		       this.mobile3 = mobilePhonelist.get(2);
			}
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getMobile4() {
		if(mobilePhonelist.size() >= 4){
		       this.mobile4 = mobilePhonelist.get(3);
			}
		return mobile4;
	}
	public void setMobile4(String mobile4) {
		this.mobile4 = mobile4;
	}
	

	
	public Users(){
		mobilePhonelist.clear();
	}
	public String getName() {
		return name;
	}
	
	public String getAge(){
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
	
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public ArrayList<String> getFixPhoneList() {
		return fixPhoneList;
	}
	public void setFixPhoneList(ArrayList<String> fixPhoneList) {
		this.fixPhoneList = fixPhoneList;
	}
	public String getFixPhone1() {
		if(fixPhoneList.size() >=1 ){
			fixPhone1 = fixPhoneList.get(0);
		}
		return fixPhone1;
	}
	public void setFixPhone1(String fixPhone1) {
		this.fixPhone1 = fixPhone1;
	}
	public String getFixPhone2() {
		if(fixPhoneList.size() >=2 ){
			fixPhone2 = fixPhoneList.get(1);
		}
		return fixPhone2;
	}
	public void setFixPhone2(String fixPhone2) {
		this.fixPhone2 = fixPhone2;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
	
	

}
