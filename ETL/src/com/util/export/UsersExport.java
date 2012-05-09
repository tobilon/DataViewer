package com.util.export;

import java.util.ArrayList;

import com.util.tools.IdcardUtils;
import com.util.tools.Log;
import com.util.tools.StringUtil;

public class UsersExport {

	public static int count = 0;

   public static void anasys(String filename,ArrayList<String> infoList){
		
		//去掉空格
		StringUtil.deleteBlankList(infoList);
		
		//分类
		String info = null;
		Users data = new Users();
		data.dataSource = filename;
		
		for(int loop = 0; loop < infoList.size(); loop++){
			//数字
			info = infoList.get(loop);
			//System.out.println(info);
			if(info.length() <1){
				continue;
			}
			
			if(StringUtil.isNum(info.charAt(0))){
				if(info.length() == 1){
					if(info.equals("M")){
						data.sex = "男";
					}
					else if(info.equals("F")){
						data.sex = "女";
					}
					continue;
				}
				if(StringUtil.isFixPhone(info)){
					data.fixPhone = info;
					continue;
				}
				else if(StringUtil.isPost(info)){
					data.post = info;
					continue;
				}
				else if(StringUtil.isFixPhone(info)){
					data.fixPhone = info;
					continue;
				}
				else if(StringUtil.isMail(info)){
					data.mail = info;
					continue;
				}
				else if(IdcardUtils.validateCard(info)){
					data.idCard = info;
					data.age = IdcardUtils.getAgeByIdCard(info);
					data.born = IdcardUtils.getBirthByIdCard(info);
					if(IdcardUtils.getGenderByIdCard(info).equals("M")){
						data.sex = "男";
					}
					else{
						data.sex = "女";
					}
					continue;
				}
				else{
					StringUtil.getMobilePhonelList(info,data.mobilePhonelist);
				}
				
			}
			else if(StringUtil.isChinese(info)){
			    if(info.length() == 1){
			    	if(info.equals("男")){
			    		data.sex ="男";
			    	}
			    	else if(info.equals("女 ")){
			    		data.sex = "女";
			    	}
			    	continue;
			    }
			    else if(StringUtil.countChina(info)>= 2 && StringUtil.countChina(info) <= 4 && data.name.length() < 2){
				  // System.out.println("name "+info);
					if(StringUtil.isName(info)){
					 data.name = info;
					 continue;
					}
				}
				else if(StringUtil.countChina(info) >= 4){
					//System.out.println(info);
					if(StringUtil.isAddress(info) && info.length() > data.address.length()){
						data.address = info;
						continue;
					}
				}
				else{
					if(StringUtil.isMail(info)){
						data.mail = info;
						continue;
					}	
				}				
			}
			//只需要判邮政编码
			else{
				if(StringUtil.isMail(info)){
					data.mail = info;
					continue;
				}	
			}
		}
	
     printlog(data);
   }


static void printlog(Users data){
	String phone="";
	for(int loop = 0; loop < data.mobilePhonelist.size(); loop++){
		phone+=data.mobilePhonelist.get(loop);
	}
	count++;
	String content = "--- num: "+count+"src: "+data.dataSource+"name: "+data.name+"addr: "+data.address+" "+data.age+" "+
	data.born+"com: "+data.company+"fix: "+data.fixPhone+"id: "+data.idCard+"mail: "
	+data.mail+"post: "+data.post+"phone: "+phone+"sex: "+data.sex+"  -------";
	
	//Log.error(content);
	System.out.println(content);
	content = null;
	
}



}
