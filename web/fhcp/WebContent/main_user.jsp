<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="session.jsp"%>
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
      <TD width="36%" height=23>&nbsp;&nbsp;&nbsp;ϵͳ�������й�ʯ�ʹ�ѧ�ִ�Զ�̽���</TD>
      <TD width="64%">����ֱ��ʣ�1024 * 768 </TD>
    </TR>
    <TR> 
      <TD height=23>&nbsp;&nbsp;&nbsp;�ͻ������ͣ�(IP:<%= request.getRemoteAddr() %>)</TD>
      <TD>&nbsp;</TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;&nbsp;&nbsp;�û���Ϣ��<font class=red><%
	  	DispMaster dispmaster = new DispMaster();
		dispmaster.setUserName(userName);
		Master master = dispmaster.nameToMaster();
		out.println(master.getUserTruename());	  
	  %></font></TD>
      <TD><div align="right"></div></TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;&nbsp;&nbsp;��½����<font class=red><%=userName%></font></TD>
      <TD>&nbsp;</TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;&nbsp;&nbsp;�����ţ�<font class=red></font></TD>
      <TD>&nbsp;</TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;</TD>
      <TD>&nbsp;</TD>
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
