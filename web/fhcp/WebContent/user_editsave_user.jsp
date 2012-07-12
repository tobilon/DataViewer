<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="session.jsp"%>
<%try{

	String pass = new String(request.getParameter("pass").getBytes("ISO8859_1"),"GBK");
	int id = Integer.parseInt(request.getParameter("id"));

	//String truename = new String(request.getParameter("truename").getBytes("ISO8859_1"),"GBK");
	//String ipaddress = new String(request.getParameter("ipaddress").getBytes("ISO8859_1"),"GBK");

	MasterControl mastercontrol = new MasterControl();
	mastercontrol.setUserPassword(pass);
	mastercontrol.setID(id);
	mastercontrol.modifymasteruser();%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta HTTP-EQUIV=REFRESH CONTENT='4; URL=user_edit_user.jsp'>
<title>财务管理系统</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
	<TR height=25>
	  <Th align=center><B class="whitetitle">修改密码信息成功</B></Th>
	</TR>
	<TR><TD align=center bgcolor="f1f3f5"><BR>
	  本页面将在<b><span id=yu>3</span></b>秒后自动返回修改密码页面，您可以选择以下操作：<BR>
	  <BR>
	<li><a href="main_user.jsp">返回首页</a><br>
	</li>
	<br>
	<br>
	<li><a href="user_edit_user.jsp">返回修改密码页面</a><br>
	  <br>
	</li>
	</TD></TR>
</TABLE>

	<script>
	function 
	countDown(secs){yu.innerText=secs;if(--secs>0)setTimeout("countDown("+secs+")",1000);}countDown(3);
	</script>
	<%
	}
	catch(Exception e){
	out.println("错误信息:"+e.getMessage());
	}%>