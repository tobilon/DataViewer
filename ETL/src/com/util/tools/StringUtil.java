package com.util.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class StringUtil {

	 static String[] company = {"学校",
			                    "公司",
			                    "银行"};
	 
	 static String[] firstname ={"赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫", 
		 "蒋","沈","韩","杨","朱","秦","尤","许","何","吕","施","张", 
		 "孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻", 
		 "柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎", 
		 "鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳", 
		 "酆","鲍","史","唐","费","廉","岑","薛","雷","贺","倪","汤", 
		 "滕","殷","罗","毕","郝","邬","安","常","乐","于","时","傅", 
		 "皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄", 
		 "和","穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄", 
		 "米","贝","明","臧","计","伏","成","戴","谈","宋","茅","庞", 
		 "熊","纪","舒","屈","项","祝","董","梁","杜","阮","蓝","闵", 
		 "席","季","麻","强","贾","路","娄","危","江","童","颜","郭", 
		 "梅","盛","林","刁","锺","徐","邱","骆","高","夏","蔡","田", 
		 "樊","胡","凌","霍","虞","万","支","柯","昝","管","卢","莫", 
		 "经","房","裘","缪","干","解","应","宗","丁","宣","贲","邓", 
		 "郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚", 
		 "程","嵇","邢","滑","裴","陆","荣","翁","荀","羊","於","惠", 
		 "甄","麴","家","封","芮","羿","储","靳","汲","邴","糜","松", 
		 "井","段","富","巫","乌","焦","巴","弓","牧","隗","山","谷", 
		 "车","侯","宓","蓬","全","郗","班","仰","秋","仲","伊","宫", 
		 "宁","仇","栾","暴","甘","钭","历","戎","祖","武","符","刘", 
		 "景","詹","束","龙","叶","幸","司","韶","郜","黎","蓟","溥", 
		 "印","宿","白","怀","蒲","邰","从","鄂","索","咸","籍","赖", 
		 "卓","蔺","屠","蒙","池","乔","阳","郁","胥","能","苍","双", 
		 "闻","莘","党","翟","谭","贡","劳","逄","姬","申","扶","堵", 
		 "冉","宰","郦","雍","却","璩","桑","桂","濮","牛","寿","通", 
		 "边","扈","燕","冀","僪","浦","尚","农","温","别","庄","晏", 
		 "柴","瞿","阎","充","慕","连","茹","习","宦","艾","鱼","容", 
		 "向","古","易","慎","戈","廖","庾","终","暨","居","衡","步", 
		 "都","耿","满","弘","匡","国","文","寇","广","禄","阙","东", 
		 "欧","殳","沃","利","蔚","越","夔","隆","师","巩","厍","聂", 
		 "晁","勾","敖","融","冷","訾","辛","阚","那","简","饶","空", 
		 "曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相", 
		 "查","后","荆","红","游","竺","权","逮","盍","益","桓","公", 
		 "万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方","赫连","皇甫","尉迟","公羊", 
		 "澹台","公冶","宗政","濮阳","淳于","单于","太叔","申屠","公孙","仲孙","轩辕","令狐", 
		 "钟离","宇文","长孙","慕容","司徒","司空","召","有","舜","叶赫那拉","丛","岳", 
		 "寸","贰","皇","侨","彤","竭","端","赫","实","甫","集","象", 
		 "翠","狂","辟","典","良","函","芒","苦","其","京","中","夕", 
		 "之","章佳","那拉","冠","宾","香","果","依尔根觉罗","依尔觉罗","萨嘛喇","赫舍里","额尔德特", 
		 "萨克达","钮祜禄","他塔喇","喜塔腊","讷殷富察","叶赫那兰","库雅喇","瓜尔佳","舒穆禄","爱新觉罗","索绰络","纳喇", 
		 "乌雅","范姜","碧鲁","张廖","张简","图门","太史","公叔","乌孙","完颜","马佳","佟佳", 
		 "富察","费莫","蹇","称","诺","来","多","繁","戊","朴","回","毓", 
		 "税","荤","靖","绪","愈","硕","牢","买","但","巧","枚","撒", 
		 "泰","秘","亥","绍","以","壬","森","斋","释","奕","姒","朋", 
		 "求","羽","用","占","真","穰","翦","闾","漆","贵","代","贯", 
		 "旁","崇","栋","告","休","褒","谏","锐","皋","闳","在","歧", 
		 "禾","示","是","委","钊","频","嬴","呼","大","威","昂","律", 
		 "冒","保","系","抄","定","化","莱","校","么","抗","祢","綦", 
		 "悟","宏","功","庚","务","敏","捷","拱","兆","丑","丙","畅", 
		 "苟","随","类","卯","俟","友","答","乙","允","甲","留","尾", 
		 "佼","玄","乘","裔","延","植","环","矫","赛","昔","侍","度", 
		 "旷","遇","偶","前","由","咎","塞","敛","受","泷","袭","衅", 
		 "叔","圣","御","夫","仆","镇","藩","邸","府","掌","首","员", 
		 "焉","戏","可","智","尔","凭","悉","进","笃","厚","仁","业", 
		 "肇","资","合","仍","九","衷","哀","刑","俎","仵","圭","夷", 
		 "徭","蛮","汗","孛","乾","帖","罕","洛","淦","洋","邶","郸", 
		 "郯","邗","邛","剑","虢","隋","蒿","茆","菅","苌","树","桐", 
		 "锁","钟","机","盘","铎","斛","玉","线","针","箕","庹","绳", 
		 "磨","蒉","瓮","弭","刀","疏","牵","浑","恽","势","世","仝", 
		 "同","蚁","止","戢","睢","冼","种","涂","肖","己","泣","潜", 
		 "卷","脱","谬","蹉","赧","浮","顿","说","次","错","念","夙", 
		 "斯","完","丹","表","聊","源","姓","吾","寻","展","出","不", 
		 "户","闭","才","无","书","学","愚","本","性","雪","霜","烟", 
		 "寒","少","字","桥","板","斐","独","千","诗","嘉","扬","善", 
		 "揭","祈","析","赤","紫","青","柔","刚","奇","拜","佛","陀", 
		 "弥","阿","素","长","僧","隐","仙","隽","宇","祭","酒","淡", 
		 "塔","琦","闪","始","星","南","天","接","波","碧","速","禚", 
		 "腾","潮","镜","似","澄","潭","謇","纵","渠","奈","风","春", 
		 "濯","沐","茂","英","兰","檀","藤","枝","检","生","折","登", 
		 "驹","骑","貊","虎","肥","鹿","雀","野","禽","飞","节","宜", 
		 "鲜","粟","栗","豆","帛","官","布","衣","藏","宝","钞","银", 
		 "门","盈","庆","喜","及","普","建","营","巨","望","希","道", 
		 "载","声","漫","犁","力","贸","勤","革","改","兴","亓","睦", 
		 "修","信","闽","北","守","坚","勇","汉","练","尉","士","旅", 
		 "五","令","将","旗","军","行","奉","敬","恭","仪","母","堂", 
		 "丘","义","礼","慈","孝","理","伦","卿","问","永","辉","位", 
		 "让","尧","依","犹","介","承","市","所","苑","杞","剧","第", 
		 "零","谌","招","续","达","忻","六","鄞","战","迟","候","宛", 
		 "励","粘","萨","邝","覃","辜","初","楼","城","区","局","台", 
		 "原","考","妫","纳","泉","老","清","德","卑","过","麦","曲", 
		 "竹","百","福","言","第五","佟","爱","年","笪","谯","哈","墨", 
		 "南宫","赏","伯","佴","佘","牟","商","西门","东门","左丘","梁丘","琴", 
		 "后","况","亢","缑","帅","微生","羊舌","海","归","呼延","南门","东郭", 
		 "百里","钦","鄢","汝","法","闫","楚","晋","谷梁","宰父","夹谷","拓跋", 
		 "壤驷","乐正","漆雕","公西","巫马","端木","颛孙","子车","督","仉","司寇","亓官", 
		 "鲜于","锺离","盖","逯","库","郏","逢","阴","薄","厉","稽","闾丘", 
		 "公良","段干","开","光","操","瑞","眭","泥","运","摩","伟","铁", 
		 "迮"};
	
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
		 
		 String phoneRegexp = "^\\d{3,4}-?\\d{7,8}$";//固话的匹配模式 
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

		　　for (int i = 0; i <= m.groupCount(); i++) {

		　　count = count + 1;

		　　}

		　　}*/
		if(m.find()){
			return true;
		}
		return false;
	 }
	 
	 public static boolean isAddress(String str){
		 
		 for(int loop = 0 ; loop < str.length(); loop++){
			 if(str.charAt(loop) == '省' ||
			    str.charAt(loop) == '县' ||
			   str.charAt(loop) == '市' ||
			   str.charAt(loop) == '区' ||
			   str.charAt(loop) == '镇' ){
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
		if(str.contentEquals("上海")||
				str.contentEquals("北京")||
				str.contentEquals("天津")||
				str.contentEquals("重庆")){
			return str;
		}
		
		int index = str.indexOf("省");
		return str.substring(0, index);

	}
	
	public static String getCity(String str){
		if(str.contentEquals("上海")||
				str.contentEquals("北京")||
				str.contentEquals("天津")||
				str.contentEquals("重庆")){
			return str;
		}
		
		int index = str.indexOf("省");
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
