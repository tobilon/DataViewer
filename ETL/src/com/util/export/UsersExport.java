package com.util.export;

import java.util.ArrayList;

import com.util.tools.IdcardUtils;
import com.util.tools.Log;
import com.util.tools.StringUtil;

public class UsersExport {

  public static int count = 0;
  /*
   * dirname: 类型名
   * filename：文件 名
   * headList: 表头
   * infoList：行记录
   * isFirstRow：是否 是第一行 ，TRUE 为真 ，ACCESS情况下总为假，ACCESS 第一行为真，其他为假
   */
  public static void anasys(String dirname,String filename,ArrayList<String> headList,ArrayList<String> infoList
		                     ,boolean isFirstRow){
		
		//去掉空格
		StringUtil.deleteBlankList(infoList);
		StringUtil.deleteBlankList(headList);
		
		//分类
		String info = null;
		Users data = new Users();
		
		data.usertype = dirname;
		data.dataSource = filename;
		
		for(int loop = 0; loop < infoList.size(); loop++){
			//数字
			info = infoList.get(loop);
			//System.out.println(info);
			if(info.length() <1){
				continue;
			}
			//精确查询
			if(StringUtil.isNum(info.charAt(0))){
				AccurateAnasys.accurate(data, info);
			}
			//模糊查询
			else if(StringUtil.isChinese(info)){
				FuzzyAnasys.fuzzy(data,info,headList.get(loop),isFirstRow);
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
	String content = "--- num: "+count+" src: "+data.dataSource+" name: "+data.name+"addr: "+data.address+" born: "+
	data.born+" com: "+data.company+" fix: "+data.getFixPhone1()+" id: "+data.idCard+" mail: "
	+data.mail+" post: "+data.post+" phone: "+phone+" sex: "+data.sex+"  -------";
	
	if(data.post.length() == 6){
		System.out.println(data.post);
	}
	
	//Log.error(content);
	//System.out.println(content);
	content = null;
	
}



}
