package com.util.db;

public class CustomerReport {

	private Long id;
	private String filename  ="";
	private String usertype  ="";
	private Long usernum=0L;
	private Long  userImport=0L;
	private Long  userMerge=0L;
	private String time;
	
	
	
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getUserMerge() {
		return userMerge;
	}
	public void setUserMerge(Long userMerge) {
		this.userMerge = userMerge;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Long getUsernum() {
		return usernum;
	}
	public void setUsernum(Long usernum) {
		this.usernum = usernum;
	}
	public Long getUserImport() {
		return userImport;
	}
	public void setUserImport(Long userImport) {
		this.userImport = userImport;
	}
	
	
}
