<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%int id = Integer.parseInt(request.getParameter("id"));
	ClassControl classcontrol= new ClassControl();
	classcontrol.setID(id);
	classcontrol.delclass();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta HTTP-EQUIV=REFRESH CONTENT='0; URL=class_manage.jsp'>
<title>南京凤凰藏品数据平台</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body>
	<span id=yu></span><a href=javascript:countDown></a>
	<script>
	function 
	countDown(secs){yu.innerText=secs;if(--secs>0)setTimeout("countDown("+secs+")",1000);}countDown(0);
	</script>
</body>
</html>