/** ToUnicode.java */
package fhcp;

import java.io.*;

/**
* �ַ���ת����Unicode�����
* @author trowa
* @date 2003-03-05
*/
public class ToUnicode {

/**
* ���ַ���ת����Unicode��
* @param strText ��ת�����ַ���
* @param code ת��ǰ�ַ����ı��룬��"GBK"
* @return ת�����Unicode���ַ���
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
