<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<title>财务管理系统</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<script language="JavaScript" type="text/JavaScript">
function delpay()
{
   if(confirm("确定要删除此吗？"))
     return true;
   else
     return false;	 
}
</script>
</head>
<body topmargin=0>
<TABLE width=90% border=0 align=center cellPadding=5 cellSpacing=1 class="tableBorder">
	<TBODY>
	<TR>
		<Th colspan=9 align=center><B class="whitetitle">业务管理</B></Th>
	</TR>
	<TR align=center bgcolor="#f1f3f5">
		<TD width=10%>短信编号</TD>
		<TD width=25%>短信内容</TD>
		<TD width=15%>短信时间</TD>
		<TD width=10%>操作</TD>
		<TD width=8%>发送次数</TD>
		<TD width=8%>响应次数</TD>
		<TD width=8%>订购次数</TD>
		<TD width=8%>响应率</TD>
		<TD width=8%>订购率</TD>
	</TR>

	<%
	DispService dispsvr = new DispService();
	ServiceControl svrCtrl = new ServiceControl();
	Vector listsvr = new Vector();
	listsvr = dispsvr.allService();
	
	//分页程序开始
	int data_num=listsvr.size();
	DisPage dispage=new DisPage();
	int Current_Page = 0;
	String currentpage=(String)request.getParameter("currentpage");
	if (currentpage != null && !currentpage.equals(""))
	{Current_Page = Integer.parseInt(request.getParameter("currentpage"));}

	dispage.Init(Current_Page,data_num);

	int l_start = dispage.getStart();
	int l_end = dispage.getEnd();
	
	for(int i=l_start;i<l_end;i++) {
	Service svrinfo = (Service)listsvr.elementAt(i);
	int userCount = svrCtrl.getServiceUserCount(svrinfo.getID());
	int ackCount = svrCtrl.getServiceAckCount(svrinfo.getID());
	int dealCount = svrCtrl.getServiceDealCount(svrinfo.getID());
	int ackRate = 0;
	int dealRate = 0;
	if(userCount != 0)
	{
		ackRate = ackCount*100/userCount;
		dealRate = dealCount*100/userCount;
	}%>
	<TR>
		<TD class=forumRow><div align="center"><%=svrinfo.getID()%></div></TD>
		<TD class=forumRow><div align="left"><%=svrinfo.getSvrContent()%></A></div></TD>
		<TD class=forumRow><div align="center"><%=svrinfo.getSvrDate()%></A></div></TD>
		<TD class=forumRow><div align="center"><A HREF="service_mark.jsp?id=<%=svrinfo.getID()%>">标记</A></div></TD>
		<TD class=forumRow><div align="center"><%=userCount%></div>
		<TD class=forumRow><div align="center"><%=ackCount%></div>
		<TD class=forumRow><div align="center"><%=dealCount%></div>
		<TD class=forumRow><div align="center"><%=ackRate+"%" %></div>
		<TD class=forumRow><div align="center"><%=dealRate+"%" %></div>
	</TR>
	
	<%}%>
	<TR >
	  <TD height="27" colspan=9 class=forumRow><div align="center">记录总条数：<%=data_num%> 
	  当前页<%=Current_Page+1%>/<%=dispage.getTotalpage()%>总页数 <a href="service_manage.jsp?currentpage=0" class="red">首页</a> <a href="service_manage.jsp?currentpage=<%=dispage.getPrepage()%>" class="red">上一页</a> <a href="service_manage.jsp?currentpage=<%=dispage.getNextpage()%>" class="red">下一页</a> <a href="service_manage.jsp?currentpage=<%=dispage.getTotalpage()-1%>" class="red">末页</a> </div></TD>
	  </TR>
	</TBODY>
</TABLE>
