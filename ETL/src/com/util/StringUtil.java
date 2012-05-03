package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import com.log.Log;

public class StringUtil {

	static ArrayList<String> phonelist = new ArrayList<String>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
           System.out.print(deleteBlank(" i not hee"));
	}
	
	
	public static String deleteBlank(String str){
		if(str == null){
			Log.error("str is null");
			return "";
		}
		
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	
	}
	
	public static void deleteBlankList(List<String> list){
		if(list == null){
			Log.error("list is null");
			return;
		}
		
		for(int listNum = 0; listNum < list.size(); listNum++){
			list.set(listNum,deleteBlank(list.get(listNum)));
		}
	}
    
	
	 //STEP 1 GETALL NUM
	 public static ArrayList<String> getAllNum(String str)
	 {
		 
		 phonelist.clear();
		 
		 for(int i = 0; i< str.length(); i++)
		 {
			 if(str.charAt(i) == '1' && str.length() - i >= 11)
			 { 
				 if(true == isMobileNO(str.substring(i, i+11)))
				 {
					 phonelist.add(str.substring(i, i+11));
					// ImportPanel.num++;
				 }
			 }
		 }
		 return phonelist;
	 }

	 public static boolean isMobileNO(String mobiles)
	 { 
		 Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
		 Matcher m = p.matcher(mobiles); 
		// System.out.println(m.matches()+"---"); 
		 return m.matches(); 
	 }
	 
	 
	 public static boolean isMail(String mail){
		 if(mail == null){
			 Log.error("mail is null");
			 return false;
		 }
		return mail.matches("\\w+@\\w+\\.\\w+");
	 }

}
