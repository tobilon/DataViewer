package com.util.export;

import java.util.ArrayList;

import com.util.db.CustomerProfile;
import com.util.tools.StringUtil;

public class FuzzyAnasys {

	public static String name[] = {"姓名,开户人,联系人,业主"};
	public static String address[] = {"地址"};
	public static String company[] = {"单位"};
	public static String actor[]={"职务"};
	
	public static void fuzzy(CustomerProfile data,String info,String headName,boolean isFirstRow)
	{
		if(true != isFirstRow){
			
			for(int nameloop = 0; nameloop < name.length; nameloop++){
				if(headName.equals(name[nameloop]) && data.name !=null && StringUtil.isName(info)){
					data.name = info;
					return;
				}
			}
			
	       for(int addressLoop = 0; addressLoop < address.length; addressLoop++){
			if(headName.equals(address[addressLoop]) && StringUtil.countChina(info) >= 4){
						data.address = info;
						return;
					}
	       }
	       
		  for(int comLoop = 0; comLoop < company.length; comLoop++){
			 if(headName.equals(company[comLoop]) && StringUtil.countChina(info) >= 4){
					data.company = info;
					return;
				} 
		 }
		  
		  for(int comLoop = 0; comLoop < actor.length; comLoop++){
				 if(headName.equals(actor[comLoop]) && StringUtil.countChina(info) >= 2){
						data.actor = info;
						return;
					} 
			 }
		  
		  
		}
	
		
		if(info.length() == 1){
	    	if(info.equals("男")){
	    		data.sex ="男";
	    	}
	    	else if(info.equals("女 ")){
	    		data.sex = "女";
	    	}
	    	return;
	    }
		else if(StringUtil.isActor(info)){
			data.actor = info;
			return;
		}
	    else if(StringUtil.countChina(info)>= 2 && StringUtil.countChina(info) <= 4 && data.name.length() < 2){
		  // System.out.println("name "+info);
	    	if(data.name.length() > 1){
	    		return ;
	    	}
			if(StringUtil.isName(info)){
			 data.name = info;
			 return;
			}
		}
		else if(StringUtil.countChina(info) >= 4){
			//System.out.println(info);
			if(data.address.length() > 1){
	    		return ;
	    	}
			if(StringUtil.isAddress(info) &&  data.address.length() <= 10){
				data.address = info;
				return;
			}
			
		    if(StringUtil.isCompany(info) &&  data.company.length() <= 1){
		    	data.company = info;
		    	return;
		    }
		}
		else{
			if(StringUtil.isMail(info)){
				data.mail = info;
				return;
			}	
		}				
	}
}
