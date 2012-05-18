package com.util.export;

import java.util.ArrayList;

import com.util.db.CustomerProfile;
import com.util.tools.StringUtil;

public class FuzzyAnasys {

	public static String name[] = {"����"};
	public static String address[] = {"��ַ"};
	public static String company[] = {"��˾"};
	
	public static void fuzzy(CustomerProfile data,String info,String headName,boolean isFirstRow)
	{
		if(true != isFirstRow){
			
			for(int nameloop = 0; nameloop < name.length; nameloop++){
				if(headName.equals(name[nameloop])){
					data.name = info;
					return;
				}
			}
			
	       for(int addressLoop = 0; addressLoop < address.length; addressLoop++){
			if(headName.equals(address[addressLoop])){
						data.address = info;
						return;
					}
	       }
	       
		  for(int comLoop = 0; comLoop < company.length; comLoop++){
			 if(headName.equals(company[comLoop])){
					data.company = info;
					return;
				} 
		 }
		}
		
		
		if(info.length() == 1){
	    	if(info.equals("��")){
	    		data.sex ="��";
	    	}
	    	else if(info.equals("Ů ")){
	    		data.sex = "Ů";
	    	}
	    	return;
	    }
	    else if(StringUtil.countChina(info)>= 2 && StringUtil.countChina(info) <= 4 && data.name.length() < 2){
		  // System.out.println("name "+info);
			if(StringUtil.isName(info)){
			 data.name = info;
			 return;
			}
		}
		else if(StringUtil.countChina(info) >= 4){
			//System.out.println(info);
			if(StringUtil.isAddress(info) && info.length() > data.address.length()){
				data.address = info;
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
