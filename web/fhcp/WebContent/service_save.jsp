<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
	String svrContent = request.getParameter("service_content");
	String svrDate = request.getParameter("service_date");
	
	ServiceControl svrcontrol = new ServiceControl();
	svrcontrol.setSvrContent(svrContent);
	svrcontrol.setSvrDate(svrDate);
	svrcontrol.addservice();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta HTTP-EQUIV=REFRESH CONTENT='4; URL=service_manage.jsp'>
<title>财务管理系统</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
	<TR>
	  <Th height="25" align=center><B>用户添加成功</B></Th>
	</TR>
	<TR><TD align=center class="forumrow"><BR>
	  本页面将在<b><span id=yu>3</span></b>秒后自动返回业务管理页面，您可以选择以下操作：<BR>
	  <BR>
	<li><a href="main.jsp">返回首页</a></li><br><br>
	<li><a href='service_add.jsp'>继续添加业务</a></li>
	<br><br>
	<li><a href="service_manage.jsp">返回用户管理页面</a><br>
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
	<%
	//}//else over%>
	<%
	}
	catch(Exception e){
	out.println("错误信息:"+e.getMessage());
	}%>
