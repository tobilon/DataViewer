<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
	String id = new String(request.getParameter("id").getBytes("ISO8859_1"),"GBK");
	String numlist = new String(request.getParameter("number_list").getBytes("ISO8859_1"),"GBK");
	String marktype = new String(request.getParameter("mark_type").getBytes("ISO8859_1"),"GBK");
	
	ServiceControl svrCtrl = new ServiceControl();
	String s[] = numlist.split("\r\n");
	
	
	for(int i = 0; i < s.length; i ++)
	{
		if(marktype.equals("user_send"))
		{
			svrCtrl.addservicelog(s[i], Long.parseLong(id));
		}
		else if(marktype.equals("user_ack"))
		{
			svrCtrl.updateLogAck(s[i], Long.parseLong(id));
		}
		else
		{
			svrCtrl.updateLogDeal(s[i], Long.parseLong(id));
		}
	}
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
	<TR height=25>
	  <Th align=center><B class="whitetitle">标记用户成功</B></Th>
	</TR>
	<TR><TD align=center bgcolor="f1f3f5"><BR>
	  本页面将在<b><span id=yu>3</span></b>秒后自动返回用户管理页面，您可以选择以下操作：<BR>
	  <BR>
	<li><a href="main.jsp">返回首页</a><br>
	</li>
	<br>
	<li><a href='service_mark.jsp?id=<%=id%>'>继续标记用户</a><br>
	</li>
	<br>
	<li><a href="service_manage.jsp">返回业务管理页面</a><br>
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