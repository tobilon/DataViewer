<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="session.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<title>南京凤凰藏品数据平台</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE class=tableBorder cellSpacing=0 cellPadding=4 width="98%" align=center 
border=0>
  <TBODY>
    <TR> 
      <TH class=tableHeaderText colSpan=2 height=22>系 统 信 息</TH>
    <TR> 
      <TD colSpan=2 height=22>&nbsp;该系统由中国石油大学现代远程教育自主开发使用，<font color="#FF0000"> 
        财务管理系统</font>Version1.0 。</TD>
    </TR>
    <TR> 
      <TD width="36%" height=23>&nbsp;&nbsp;&nbsp;系统开发：中国石油大学现代远程教育</TD>
      <TD width="64%">建议分辨率：1024 * 768 </TD>
    </TR>
    <TR> 
      <TD height=23>&nbsp;&nbsp;&nbsp;客户端类型：(IP:<%= request.getRemoteAddr() %>)</TD>
      <TD>&nbsp;</TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;&nbsp;&nbsp;用户信息：<font class=red><%
	  	DispMaster dispmaster = new DispMaster();
		dispmaster.setUserName(userName);
		Master master = dispmaster.nameToMaster();
		out.println(master.getUserTruename());	  
	  %></font></TD>
      <TD><div align="right"></div></TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;&nbsp;&nbsp;登陆名：<font class=red><%=userName%></font></TD>
      <TD>&nbsp;</TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;&nbsp;&nbsp;管理部门：<font class=red></font></TD>
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
