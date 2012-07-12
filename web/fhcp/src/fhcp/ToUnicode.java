/** ToUnicode.java */
package fhcp;

import java.io.*;

/**
* 字符串转换成Unicode码的类
* @author trowa
* @date 2003-03-05
*/
public class ToUnicode {

/**
* 把字符串转换成Unicode码
* @param strText 待转换的字符串
* @param code 转换前字符串的编码，如"GBK"
* @return 转换后的Unicode码字符串
*/
public String toUnicode(String strText,String code) throws UnsupportedEncodingException{
	char c;
	String strRet = "" ;
	int intAsc;
	String strHex;
	strText = new String(strText.getBytes("8859_1"),code);
	for ( int i = 0; i < strText.length(); i++ ){
		c = strText.charAt(i);
		intAsc = (int)c;
		if(intAsc>128){
			strHex = Integer.toHexString(intAsc);
			strRet = strRet + "&#x" + strHex+";";
		}
		else{
			strRet = strRet + c;
		}
	}
	return strRet ;
}

}
