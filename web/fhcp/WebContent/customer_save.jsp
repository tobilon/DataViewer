<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.net.URLDecoder"%>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<%
    String id = request.getParameter("id");
	String extra = request.getParameter("extra");
	int usertype = 0;
	int i = 0;
	while(i < 32)
	{
		String strType = request.getParameter("qtypevalue"+(1<<i));
		if(strType != null && "1".equals(strType))
		{
			usertype += 1<<i;
		}
		i ++;
	}
	
	CustomerControl customerCtrl = new CustomerControl();
	customerCtrl.updateUser(id, extra, usertype);
%>
<title>批量修改用户信息</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
	<TR><Th align=center class="whitetitle"><strong>用户信息修改成功,请关闭当前窗口</strong></Th>
	</TR>	
</TABLE>