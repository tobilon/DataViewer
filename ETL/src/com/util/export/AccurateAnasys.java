package com.util.export;

import java.util.HashMap;

import com.util.db.CustomerProfile;
import com.util.tools.IdcardUtils;
import com.util.tools.MobileUtil;
import com.util.tools.StringUtil;

public class AccurateAnasys {
	
	public static void accurate(CustomerProfile data,String info){
		
			if(info.length() == 1){
				if(info.equals("M")){
					data.sex = "ÄÐ";
				}
				else if(info.equals("F")){
					data.sex = "Å®";
				}
				return;
			}
			
			else if(StringUtil.isPost(info)){
				data.post = info;
				return;
			}
			else if(StringUtil.isFixPhone(info)){
				data.fixPhoneList.add(info);
				return;
			}
			else if(StringUtil.isMail(info)){
				data.mail = info;
				return;
			}
			else if(IdcardUtils.validateCard(info)){
				data.idCard = info;
				data.born = IdcardUtils.getBirthByIdCard(info);
				if(IdcardUtils.getGenderByIdCard(info).equals("M")){
					data.sex = "ÄÐ";
				}
				else{
					data.sex = "Å®";
				}
				return;
			}
			else if(StringUtil.isBorn(info)){
				data.born = info;
			}
			else{
				StringUtil.getMobilePhonelList(info,data.mobilePhonelist);
				if(data.mobilePhonelist.size() >= 1){
					HashMap map = MobileUtil.getPhoneInfo(data.getMobile1().substring(0, 7));
					
					String str = (String) map.get("city");
					if(null != str){
						data.province = StringUtil.getProvince(str);
					    data.city     = StringUtil.getCity(str);
					}
					
				}
			}
			
		}
	}

