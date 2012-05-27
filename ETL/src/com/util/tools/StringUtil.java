package com.util.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class StringUtil {

	 static String[] company = {"ѧУ",
			                    "��˾",
			                    "����"};
	 
	 static String[] firstname ={"��","Ǯ","��","��","��","��","֣","��","��","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","ʩ","��", 
		 "��","��","��","��","��","κ","��","��","��","л","��","��", 
		 "��","ˮ","�","��","��","��","��","��","��","��","��","��", 
		 "³","Τ","��","��","��","��","��","��","��","��","Ԭ","��", 
		 "ۺ","��","ʷ","��","��","��","�","Ѧ","��","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","ʱ","��", 
		 "Ƥ","��","��","��","��","��","Ԫ","��","��","��","ƽ","��", 
		 "��","��","��","��","Ҧ","��","տ","��","��","ë","��","��", 
		 "��","��","��","�","��","��","��","��","̸","��","é","��", 
		 "��","��","��","��","��","ף","��","��","��","��","��","��", 
		 "ϯ","��","��","ǿ","��","·","¦","Σ","��","ͯ","��","��", 
		 "÷","ʢ","��","��","��","��","��","��","��","��","��","��", 
		 "��","��","��","��","��","��","֧","��","��","��","¬","Ī", 
		 "��","��","��","��","��","��","Ӧ","��","��","��","��","��", 
		 "��","��","��","��","��","��","��","ʯ","��","��","ť","��", 
		 "��","��","��","��","��","½","��","��","��","��","�","��", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","ɽ","��", 
		 "��","��","�","��","ȫ","ۭ","��","��","��","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "��","ղ","��","��","Ҷ","��","˾","��","۬","��","��","��", 
		 "ӡ","��","��","��","��","ۢ","��","��","��","��","��","��", 
		 "׿","��","��","��","��","��","��","��","��","��","��","˫", 
		 "��","ݷ","��","��","̷","��","��","��","��","��","��","��", 
		 "Ƚ","��","۪","Ӻ","ȴ","�","ɣ","��","�","ţ","��","ͨ", 
		 "��","��","��","��","�h","��","��","ũ","��","��","ׯ","��", 
		 "��","��","��","��","Ľ","��","��","ϰ","��","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","»","��","��", 
		 "ŷ","�","��","��","ε","Խ","��","¡","ʦ","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "��","��","ɳ","ؿ","��","��","��","��","��","��","��","��", 
		 "��","��","��","��","��","��","Ȩ","��","��","��","��","��", 
		 "��ٹ","˾��","�Ϲ�","ŷ��","�ĺ�","���","����","����","����","�ʸ�","ξ��","����", 
		 "�̨","��ұ","����","���","����","����","̫��","����","����","����","��ԯ","���", 
		 "����","����","����","Ľ��","˾ͽ","˾��","��","��","˴","Ҷ������","��","��", 
		 "��","��","��","��","ͮ","��","��","��","ʵ","��","��","��", 
		 "��","��","��","��","��","��","â","��","��","��","��","Ϧ", 
		 "֮","�¼�","����","��","��","��","��","����������","��������","������","������","�������", 
		 "���˴�","ť��»","������","ϲ����","ګ�󸻲�","Ҷ������","������","�϶���","����»","���¾���","������","����", 
		 "����","����","��³","����","�ż�","ͼ��","̫ʷ","����","����","����","���","١��", 
		 "����","��Ī","�","��","ŵ","��","��","��","��","��","��","ع", 
		 "˰","��","��","��","��","˶","��","��","��","��","ö","��", 
		 "̩","��","��","��","��","��","ɭ","ի","��","��","�","��", 
		 "��","��","��","ռ","��","�","��","��","��","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "��","ʾ","��","ί","��","Ƶ","��","��","��","��","��","��", 
		 "ð","��","ϵ","��","��","��","��","У","ô","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "��","��","��","î","ٹ","��","��","��","��","��","��","β", 
		 "ٮ","��","��","��","��","ֲ","��","��","��","��","��","��", 
		 "��","��","ż","ǰ","��","��","��","��","��","��","Ϯ","��", 
		 "��","ʥ","��","��","��","��","��","ۡ","��","��","��","Ա", 
		 "��","Ϸ","��","��","��","ƾ","Ϥ","��","��","��","��","ҵ", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "��","��","��","��","Ǭ","��","��","��","��","��","��","��", 
		 "۰","��","��","��","�","��","��","��","��","��","��","ͩ", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "ĥ","��","��","��","��","��","ǣ","��","�","��","��","��", 
		 "ͬ","��","ֹ","�","�","��","��","Ϳ","Ф","��","��","Ǳ", 
		 "��","��","��","��","��","��","��","˵","��","��","��","��", 
		 "˹","��","��","��","��","Դ","��","��","Ѱ","չ","��","��", 
		 "��","��","��","��","��","ѧ","��","��","��","ѩ","˪","��", 
		 "��","��","��","��","��","�","��","ǧ","ʫ","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "��","��","��","��","ɮ","��","��","��","��","��","��","��", 
		 "��","��","��","ʼ","��","��","��","��","��","��","��","��", 
		 "��","��","��","��","��","̶","��","��","��","��","��","��", 
		 "�","��","ï","Ӣ","��","̴","��","֦","��","��","��","��", 
		 "��","��","��","��","��","¹","ȸ","Ұ","��","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","��","��","��", 
		 "��","ӯ","��","ϲ","��","��","��","Ӫ","��","��","ϣ","��", 
		 "��","��","��","��","��","ó","��","��","��","��","��","��", 
		 "��","��","��","��","��","��","��","��","��","ξ","ʿ","��", 
		 "��","��","��","��","��","��","��","��","��","��","ĸ","��", 
		 "��","��","��","��","Т","��","��","��","��","��","��","λ", 
		 "��","Ң","��","��","��","��","��","��","Է","�","��","��", 
		 "��","��","��","��","��","��","��","۴","ս","��","��","��", 
		 "��","ճ","��","��","��","��","��","¥","��","��","��","̨", 
		 "ԭ","��","�","��","Ȫ","��","��","��","��","��","��","��", 
		 "��","��","��","��","����","١","��","��","��","��","��","ī", 
		 "�Ϲ�","��","��","٦","��","Ĳ","��","����","����","����","����","��", 
		 "��","��","��","��","˧","΢��","����","��","��","����","����","����", 
		 "����","��","۳","��","��","��","��","��","����","�׸�","�й�","�ذ�", 
		 "����","����","���","����","����","��ľ","���","�ӳ�","��","��","˾��","����", 
		 "����","����","��","��","��","ۣ","��","��","��","��","��","����", 
		 "����","�θ�","��","��","��","��","��","��","��","Ħ","ΰ","��", 
		 "��"};
	
	 static HashMap<String,Integer> map = new HashMap<String,Integer>();
	 
	 static {
		 for(int loop = 0; loop < firstname.length; loop++){
			 map.put(firstname[loop], new Integer(loop));
		 }
	 }
	//static ArrayList<String> phonelist = new ArrayList<String>();
	
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
	 public static void getMobilePhonelList(String str,ArrayList<String> list)
	 {
		 
		// phonelist.clear();
		 int i = 0;
		 
		 while(i< str.length())
		 {
			 if(str.charAt(i) == '1' && str.length() - i >= 11)
			 { 
				 if(true == isMobileNO(str.substring(i, i+11)))
				 {
					 list.add(str.substring(i, i+11));
					 i = i+11;
					// ImportPanel.num++;
				 }
				 else{
					 i++;
				 }
			 }
			 else{
				 i++;
			 }
		 }
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
	 
	 public static boolean isNum(char c){
		 
		 if( c-'0' >= 0 && c-'0' <= 9){
			 return true;
		 }
		 
		 return false;
	 }
	 
	 public static boolean isFixPhone(String str){
		 
		 String phoneRegexp = "^\\d{3,4}-?\\d{7,8}$";//�̻���ƥ��ģʽ 
		 if(str == null){
			 Log.error("fixphone is null");
			 return false;
		 } 
		 
		 return str.matches(phoneRegexp);
	 }
	 
	 public static boolean isPost(String str){
		 if(str == null){
			 Log.error("mailNum is null");
			 return false;
		 }
		 return str.matches("[1-9]\\d{5}(?!\\d)");
	 }

	 public static boolean isChinese(String str){
		 if(str == null){
			 Log.error("china is null");
			 return false;
		 }
		 String regEx = "[\\u4e00-\\u9fa5]";

         Pattern p = Pattern.compile(regEx);
         Matcher m = p.matcher(str);
        /*while (m.find()) {

		����for (int i = 0; i <= m.groupCount(); i++) {

		����count = count + 1;

		����}

		����}*/
		if(m.find()){
			return true;
		}
		return false;
	 }
	 
	 public static boolean isAddress(String str){
		 
		 for(int loop = 0 ; loop < str.length(); loop++){
			 if(str.charAt(loop) == 'ʡ' ||
			    str.charAt(loop) == '��' ||
			   str.charAt(loop) == '��' ||
			   str.charAt(loop) == '��' ||
			   str.charAt(loop) == '��' ){
				 return true;
			 }
		 }
		 return false;
	 }
	 
	public static boolean isCompany(String str){
		for(int loop = 0; loop < company.length; loop++){
			if(str.contains(company[loop])){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isName(String str){
		
		Integer in = map.get(String.valueOf(str.charAt(0)));
	    if(null == in){
	    	return false;
	    }
		return true;
	}
	
	public static int countChina(String str){
		
		if(str == null){
			 Log.error("china is null");
			 return 0;
		 }
		int count = 0;
		for(int loop = 0; loop < str.length(); loop++){
			if(String.valueOf(str.charAt(loop)).getBytes().length != 1){
				count++;				
			}
			else {
				break;
			}
		}
		//System.out.println("cc --->" +count);
		return count;
		
	}
	
	public static String getProvince(String str){
		if(str.contentEquals("�Ϻ�")||
				str.contentEquals("����")||
				str.contentEquals("���")||
				str.contentEquals("����")){
			return str;
		}
		
		int index = str.indexOf("ʡ");
		return str.substring(0, index);

	}
	
	public static String getCity(String str){
		if(str.contentEquals("�Ϻ�")||
				str.contentEquals("����")||
				str.contentEquals("���")||
				str.contentEquals("����")){
			return str;
		}
		
		int index = str.indexOf("ʡ");
		return str.substring(index+1, str.length()-1);

	}
	
	public static String getFileName(String name){
		if(name == null){
			return null;
		}
		
		String[] temp = name.split("\\\\");
		return temp[temp.length - 1];
		
	}
	
	public static boolean isBorn(String info){
		if(info.length() < 6 || !info.startsWith("19")){
			return false;
		}
		return info.matches( "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|1[0-9]|2[0-9]|3[0-1])$"); 
		
	}
	public static void deleteRepeat(ArrayList<String> list){
		ArrayList<String> temp = new   ArrayList(new   HashSet<String>(list));
		
		list.clear();
		
		for(int loop = 0; loop < temp.size(); loop++){
			
			list.add(temp.get(loop));
		}
	}
}
