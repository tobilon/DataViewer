<%@ page contentType="text/html;charset=gb2312" %>
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
<TABLE width=100% border=0 align=center cellPadding=5 cellSpacing=1 class="tableBorder">
	<TBODY>
	<TR>
		<Th colspan=12 align=center><B class="whitetitle">业务管理</B></Th>
	</TR>
	<TR align=center bgcolor="#f1f3f5">
		<TD width=6%>短信编号</TD>
		<TD width=20%>短信内容</TD>
		<TD width=12%>短信时间</TD>
		<TD width=10%>操作</TD>
		<TD width=6%>发送量</TD>
		<TD width=6%>成功量</TD>
		<TD width=6%>失败量</TD>
		<TD width=6%>响应人数</TD>
		<TD width=7%>订购人数</TD>
		<TD width=7%>响应率</TD>
		<TD width=7%>订购率</TD>
		<TD width=7%>响应订购率</TD>
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
	int failCount = svrCtrl.getServiceFailCount(svrinfo.getID());
	int ackRate = 0;
	int dealRate = 0;
	int ackdealRate = 0;
	if(userCount-failCount > 0)
	{
		ackRate = ackCount*100/(userCount-failCount);
		dealRate = dealCount*100/(userCount-failCount);		
	}
	if(ackCount != 0)
	{
		ackdealRate = dealCount*100/ackCount;
	}
	%>
	<TR>
		<TD class=forumRow><div align="center"><%=svrinfo.getID()%></div></TD>
		<TD class=forumRow><div align="left"><%=svrinfo.getSvrContent()%></A></div></TD>
		<TD class=forumRow><div align="center"><%=svrinfo.getSvrDate()%></A></div></TD>
		<TD class=forumRow><div align="center"><A HREF="service_mark.jsp?id=<%=svrinfo.getID()%>">标记</A>
		<A HREF="service_user.jsp?id=<%=svrinfo.getID()%>" target="_blank">查看用户</A></div></TD>
		<TD class=forumRow><div align="center"><%=userCount%></div>
		<TD class=forumRow><div align="center"><%=userCount-failCount%></div>
		<TD class=forumRow><div align="center"><%=failCount%></div>
		<TD class=forumRow><div align="center"><%=ackCount%></div>
		<TD class=forumRow><div align="center"><%=dealCount%></div>
		<TD class=forumRow><div align="center"><%=ackRate+"%" %></div>
		<TD class=forumRow><div align="center"><%=dealRate+"%" %></div>
		<TD class=forumRow><div align="center"><%=ackdealRate+"%" %></div>
	</TR>
	
	<%}%>
	<TR >
	  <TD height="27" colspan=12 class=forumRow><div align="center">记录总条数：<%=data_num%> 
	  当前页<%=Current_Page+1%>/<%=dispage.getTotalpage()%>总页数 <a href="service_manage.jsp?currentpage=0" class="red">首页</a> <a href="service_manage.jsp?currentpage=<%=dispage.getPrepage()%>" class="red">上一页</a> <a href="service_manage.jsp?currentpage=<%=dispage.getNextpage()%>" class="red">下一页</a> <a href="service_manage.jsp?currentpage=<%=dispage.getTotalpage()-1%>" class="red">末页</a> </div></TD>
	  </TR>
	</TBODY>
</TABLE>
