<%@ page contentType="text/html; charset=gb2312"  pageEncoding="gb2312"%>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<title>南京凤凰藏品数据平台</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/header.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/header.js"></script>
</head>
<body topmargin=0>
<ul class="topnav">
    <li><a href="main.jsp">首页</a></li>
	<li>客户资源管理
		<ul class="subnav">
			<li><a href="customer_manage.jsp">客户管理</a></li>
		</ul>
	</li>
	<li>业务营销管理
		<ul class="subnav">
			<li><a href="service_manage.jsp">业务管理</a></li>
			<li><a href="service_add.jsp">新增业务</a></li>
		</ul>
	</li>
	<li>系统管理
		<ul class="subnav">
			<li><a href="class_manage.jsp">部门管理</a></li>
			<li><a href="class_add.jsp">增加部门</a></li>
			<li><a href="user_manage.jsp">用户管理</a></li>
			<li><a href="user_add.jsp">增加用户</a></li>
		</ul>
	</li>
</ul>
<br>
<br>
<br>
<TABLE class=tableBorder cellSpacing=0 cellPadding=4 width=600 align=center 
border=0>
  <TBODY>
    <TR> 
      <TH class=tableHeaderText colSpan=2 height=22>凤凰藏品客户营销管理系统</TH>
    </TR>  
    <TR>
    <TD colspan="2" align=center>
    <IMG src="images/main.gif">
    </TD>
    </TR>
    <TR> 
      <TD width="50%" height=23 align=center>&nbsp;&nbsp;&nbsp;客户资源管理：</TD>
      <TD width="50%">维护客户身份信息、联系方式</TD>
    </TR>
    <TR>
      <TD width="50%" height=23 align=center>&nbsp;&nbsp;&nbsp;营销业务管理：</TD>
      <TD width="50%">维护业务记录，分析营销数据</TD>
    </TR>
    <TR> 
      <TD width="50%" height=23 align=center>&nbsp;&nbsp;&nbsp;系统管理：</TD>
      <TD width="50%">管理操作用户及部门信息</TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;</TD>
      <TD><div align="right"></div></TD>
    </TR>
  </TBODY>
</TABLE>
<P></P>
</BODY></HTML>
