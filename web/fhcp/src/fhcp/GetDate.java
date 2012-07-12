/**
*  Title  财务管理系统
*  @author: trowa
*  Company: http://www.upol.cn
*  Copyright: Copyright (c) 2004
*  @version 1.0
*  日期
*/
package fhcp;

import java.util.*;
import java.text.*;

public class GetDate {

	public GetDate(){}

	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString=formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString=formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}
	public static String getStringDate(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	public static String getStringDateShort(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	public static Date strToDate(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos=new ParsePosition(0);
		Date strtodate=formatter.parse(strDate,pos);
		return strtodate;
	}
	public static String dateToStr(java.util.Date dateDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	public static Date strToBirthday(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos=new ParsePosition(0);
		Date strtodate=formatter.parse(strDate,pos);
		return strtodate;
	}
	public static Date getNow(){
		Date currentTime = new Date();
		return currentTime;
	}
	public static long getS(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos=new ParsePosition(0);
		Date strtodate=formatter.parse(strDate,pos);
		return strtodate.getTime();
	}

	public static Date getLastDate(long day) {
		Date date=new Date();
		long date_3_hm=date.getTime()-3600000*34*day;
		Date date_3_hm_date=new Date(date_3_hm);
		return date_3_hm_date;
	}
}

