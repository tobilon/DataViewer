<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
	String name = request.getParameter("name");
	String pass = request.getParameter("pass");
	String truename=request.getParameter("truename");
	int classid = Integer.parseInt(request.getParameter("classid"));
	String ipaddress=request.getParameter("ipaddress");

	DispMaster dispmaster = new DispMaster();
	dispmaster.setUserName(name);
	if	(dispmaster.reLogin()) {
		response.sendRedirect("error2.jsp");
	}
	else {
	MasterControl mastercontrol = new MasterControl();
	mastercontrol.setUserName(name);
	mastercontrol.setUserPassword(pass);
	mastercontrol.setUserTruename(truename);
	mastercontrol.setUserIpaddress(ipaddress);
	mastercontrol.setJoindate(GetDate.getStringDateShort());
	mastercontrol.setClassid(classid);
	mastercontrol.addmaster();%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta HTTP-EQUIV=REFRESH CONTENT='4; URL=user_manage.jsp'>
<title>南京凤凰藏品数据平台</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
	<TR>
	  <Th height="25" align=center><B>用户添加成功</B></Th>
	</TR>
	<TR><TD align=center class="forumrow"><BR>
	  本页面将在<b><span id=yu>3</span></b>秒后自动返回用户管理页面，您可以选择以下操作：<BR>
	  <BR>
	<li><a href="main.jsp">返回首页</a></li><br><br>
	<li><a href='user_add.jsp'>继续添加用户</a></li>
	<br><br>
	<li><a href="user_manage.jsp">返回用户管理页面</a><br>
	  <br></li>
	</TD></TR>
</TABLE>

	<script>
	function countDown(secs)
	{
	   yu.innerText=secs;if(--secs>0)setTimeout("countDown("+secs+")",1000);
		}
	countDown(3);
	</script>
	<%}//else over%>
	<%
	}
	catch(Exception e){
	out.println("错误信息:"+e.getMessage());
	}%>
