<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<title>�Ͼ���˲�Ʒ����ƽ̨</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/header.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/header.js"></script>
<script language="JavaScript" type="text/JavaScript">
function delpay()
{
   if(confirm("ȷ��Ҫɾ������"))
     return true;
   else
     return false;	 
}
</script>
</head>
<body topmargin=0>
<ul class="topnav">
    <li><a href="main.jsp">��ҳ</a></li>
	<li>�ͻ���Դ����
		<ul class="subnav">
			<li><a href="customer_manage.jsp">�ͻ�����</a></li>
		</ul>
	</li>
	<li>ҵ��Ӫ������
		<ul class="subnav">
			<li><a href="service_manage.jsp">ҵ�����</a></li>
			<li><a href="service_add.jsp">����ҵ��</a></li>
		</ul>
	</li>
	<li>ϵͳ����
		<ul class="subnav">
			<li><a href="class_manage.jsp">���Ź���</a></li>
			<li><a href="class_add.jsp">���Ӳ���</a></li>
			<li><a href="user_manage.jsp">�û�����</a></li>
			<li><a href="user_add.jsp">�����û�</a></li>
		</ul>
	</li>
</ul>
<br>
<br>
<br>
<TABLE width=100% border=0 align=center cellPadding=5 cellSpacing=1 class="tableBorder">
	<TBODY>
	<TR>
		<Th colspan=12 align=center><B class="whitetitle">ҵ�����</B></Th>
	</TR>
	<TR align=center bgcolor="#f1f3f5">
		<TD width=6%>���ű��</TD>
		<TD width=20%>��������</TD>
		<TD width=12%>����ʱ��</TD>
		<TD width=10%>����</TD>
		<TD width=6%>������</TD>
		<TD width=6%>�ɹ���</TD>
		<TD width=6%>ʧ����</TD>
		<TD width=6%>��Ӧ����</TD>
		<TD width=7%>��������</TD>
		<TD width=7%>��Ӧ��</TD>
		<TD width=7%>������</TD>
		<TD width=7%>��Ӧ������</TD>
	</TR>

	<%
	DispService dispsvr = new DispService();
	ServiceControl svrCtrl = new ServiceControl();
	
	Vector listsvr = new Vector();
	listsvr = dispsvr.allService();
	
	//��ҳ����ʼ
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
		<TD class=forumRow><div align="center"><A HREF="service_mark.jsp?id=<%=svrinfo.getID()%>">���</A>
		<A HREF="service_user.jsp?id=<%=svrinfo.getID()%>" target="_blank">�鿴�û�</A></div></TD>
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
	  <TD height="27" colspan=12 class=forumRow><div align="center">��¼��������<%=data_num%> 
	  ��ǰҳ<%=Current_Page+1%>/<%=dispage.getTotalpage()%>��ҳ�� <a href="service_manage.jsp?currentpage=0" class="red">��ҳ</a> <a href="service_manage.jsp?currentpage=<%=dispage.getPrepage()%>" class="red">��һҳ</a> <a href="service_manage.jsp?currentpage=<%=dispage.getNextpage()%>" class="red">��һҳ</a> <a href="service_manage.jsp?currentpage=<%=dispage.getTotalpage()-1%>" class="red">ĩҳ</a> </div></TD>
	  </TR>
	</TBODY>
</TABLE>
