package com.util.export;

import java.util.ArrayList;

import com.util.tools.IdcardUtils;
import com.util.tools.Log;
import com.util.tools.StringUtil;

public class UsersExport {

  public static int count = 0;
  /*
   * dirname: ������
   * filename���ļ� ��
   * headList: ��ͷ
   * infoList���м�¼
   * isFirstRow���Ƿ� �ǵ�һ�� ��TRUE Ϊ�� ��ACCESS�������Ϊ�٣�ACCESS ��һ��Ϊ�棬����Ϊ��
   */
  public static void anasys(String dirname,String filename,ArrayList<String> headList,ArrayList<String> infoList
		                     ,boolean isFirstRow){
		
		//ȥ���ո�
		StringUtil.deleteBlankList(infoList);
		StringUtil.deleteBlankList(headList);
		
		//����
		String info = null;
		Users data = new Users();
		
		data.usertype = dirname;
		data.dataSource = filename;
		
		for(int loop = 0; loop < infoList.size(); loop++){
			//����
			info = infoList.get(loop);
			//System.out.println(info);
			if(info.length() <1){
				continue;
			}
			//��ȷ��ѯ
			if(StringUtil.isNum(info.charAt(0))){
				AccurateAnasys.accurate(data, info);
			}
			//ģ����ѯ
			else if(StringUtil.isChinese(info)){
				FuzzyAnasys.fuzzy(data,info,headList.get(loop),isFirstRow);
			}
			//ֻ��Ҫ����������
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
