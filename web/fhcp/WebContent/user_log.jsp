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
<title>�Ͼ���˲�Ʒ����ƽ̨</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<%Vector listlog = new Vector();
DispLog displog = new DispLog();
listlog = displog.allLog();


//��ҳ����ʼ
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
		<Th height="25">��½��Ϣ</Th>
	  </TR>
	  
			<%int i=0;
			for(i=l_start;i<l_end;i++) {
			Log loginfo = (Log)listlog.elementAt(i);%> 
	<TR >
		<TD height="22" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�û� <span class="red"><%=loginfo.getUser()%> </span>�� <%=loginfo.getLogindate()%> ��½���� <%=loginfo.getLogoutdate()%> �뿪��IP��ַΪ��<%=loginfo.getIp()%></TD>
	  </TR>
	<%}%>   
	  
	<TR >
	  <TD height="27" bgcolor="#FFFFFF"><div align="center">��¼��������<%=data_num%> 
	  ��ǰҳ<%=Current_Page+1%>/<%=dispage.getTotalpage()+1%>��ҳ�� <a href="user_log.jsp?currentpage=0" class="red">��ҳ</a> <a href="user_log.jsp?currentpage=<%=dispage.getPrepage()%>" class="red">��һҳ</a> <a href="user_log.jsp?currentpage=<%=dispage.getNextpage()%>" class="red">��һҳ</a> <a href="user_log.jsp?currentpage=<%=dispage.getTotalpage()%>" class="red">ĩҳ</a> </div></TD>
	  </TR>
	</TBODY>
</TABLE>
