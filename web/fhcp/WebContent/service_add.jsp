<%@ page contentType="text/html;charset=gb2312" pageEncoding="gb2312"%>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>南京凤凰藏品数据平台</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/header.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/header.js"></script>
<script Language="JavaScript">
<!--
function check_input(theForm)
{

   if (theForm.service_content.value == "")
  {
    alert("请输入短信内容");
    theForm.service_content.focus();
    return (false);
  }

   if (theForm.service_content.value.length > 250)
    {
    alert("短信长度应小于250个字符");
    theForm.service_content.focus();
    return (false);
  }
}
//-->
</script>
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
<TABLE width=600 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="service_save.jsp" onsubmit="return check_input(this)">
	<TR bgcolor="#6699CC">
		<Th colspan=2><div align="center" class="whitetitle"><B>增加业务</B></div></Th>
	</TR>
	<TR>
	  <TD width="20%" class="forumrow">&nbsp;<strong>短信内容</strong></TD>
		<TD width="80%" class="forumrow">
	  <textarea  name=service_content rows=10 cols=60 maxlength=250></textarea><br>&nbsp;**不得超过250 个字符</TD>
	</TR>
	<TR>
	<TR>
	  <TD width="20%" class="forumrow">&nbsp;<strong>发送时间</strong></TD>
		<TD width="80%" class="forumrow">
		<input name="service_date" id="starttime2" onclick='calendar()' size="20" runat="server">&nbsp;&nbsp;&nbsp;
		</TD>
	</TR>
	<TR>
	  <TD height="45" colspan=2 align=center class="forumrow">	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="确 定"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="清 除"></FONT></TD>
	</TR>
  </form>
</TABLE>
</body>
</html>