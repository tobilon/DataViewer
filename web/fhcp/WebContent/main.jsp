<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<title>�Ͼ���˲�Ʒ����ƽ̨</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE class=tableBorder cellSpacing=0 cellPadding=4 width="98%" align=center 
border=0>
  <TBODY>
    <TR> 
      <TH class=tableHeaderText colSpan=2 height=22>ϵ ͳ �� Ϣ</TH>
    <TR> 
      <TD colSpan=2 height=22>&nbsp;��ϵͳ���й�ʯ�ʹ�ѧ�ִ�Զ�̽�����������ʹ�ã�<font color="#FF0000"> 
        �������ϵͳ</font>Version1.0 ��</TD>
    </TR>
    <TR> 
      <TD width="36%" 
      height=23>&nbsp;&nbsp;&nbsp;���������ͣ�(IP:<%= request.getServerName() %>)(�˿�:<%= request.getServerPort() %>)</TD>
      <TD width="64%">�ű��������棺Jsp 2.0</TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;&nbsp;&nbsp;�ͻ������ͣ�(IP:<%= request.getRemoteAddr() %>)</TD>
      <TD>Web��������Weblogic6.0+sp2</TD>
    </TR>
    <TR> 
      <TD width="36%" height=23>&nbsp;&nbsp;&nbsp;���ݿ��������Mysql 4.0.18 - nt</TD>
      <TD width="64%">JDBC������Mark Matthews' MySQL Driver 
      2.0.14</TD>
    </TR>
    <TR> 
      <TD height=23>&nbsp;&nbsp;&nbsp;ϵͳ�������й�ʯ�ʹ�ѧ�ִ�Զ�̽���</TD>
      <TD>����ֱ��ʣ�1024 * 768 </TD>
    </TR>
    <TR> 
      <TD height=23>&nbsp;</TD>
      <TD>&nbsp;</TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;</TD>
      <TD><div align="right"></div></TD>
    </TR>
  </TBODY>
</TABLE>
<P></P>
<TABLE align=center>
  <TBODY>
  <TR>
    <TD width="315">Copyright (c) www.upol.cn <a href="http://www.upol.cn" target="_blank"></a> 
        All Rights Reserved .</TD>
  </TR></TBODY></TABLE></BODY></HTML>
