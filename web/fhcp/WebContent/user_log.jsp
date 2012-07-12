<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%DispMaster dispmaster = new DispMaster();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*60">
<title>南京凤凰藏品数据平台</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<%Vector listlog = new Vector();
DispLog displog = new DispLog();
listlog = displog.allLog();


//分页程序开始
int data_num=displog.lognum();
DisPage dispage=new DisPage();
int Current_Page = 0;
String currentpage=(String)request.getParameter("currentpage");
if (currentpage != null && !currentpage.equals(""))
{Current_Page = Integer.parseInt(request.getParameter("currentpage"));}

dispage.Init(Current_Page,data_num);

int l_start = dispage.getStart();
int l_end = dispage.getEnd();

%>
</head>
<body topmargin=0>	
<TABLE width=98% border=0 align=center cellPadding=2 cellSpacing=1 class="tableBorder">
	<TBODY>
	<TR align=center bgcolor="#ffffff">
		<Th height="25">登陆信息</Th>
	  </TR>
	  
			<%int i=0;
			for(i=l_start;i<l_end;i++) {
			Log loginfo = (Log)listlog.elementAt(i);%> 
	<TR >
		<TD height="22" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户 <span class="red"><%=loginfo.getUser()%> </span>在 <%=loginfo.getLogindate()%> 登陆，在 <%=loginfo.getLogoutdate()%> 离开，IP地址为：<%=loginfo.getIp()%></TD>
	  </TR>
	<%}%>   
	  
	<TR >
	  <TD height="27" bgcolor="#FFFFFF"><div align="center">记录总条数：<%=data_num%> 
	  当前页<%=Current_Page+1%>/<%=dispage.getTotalpage()+1%>总页数 <a href="user_log.jsp?currentpage=0" class="red">首页</a> <a href="user_log.jsp?currentpage=<%=dispage.getPrepage()%>" class="red">上一页</a> <a href="user_log.jsp?currentpage=<%=dispage.getNextpage()%>" class="red">下一页</a> <a href="user_log.jsp?currentpage=<%=dispage.getTotalpage()%>" class="red">末页</a> </div></TD>
	  </TR>
	</TBODY>
</TABLE>
