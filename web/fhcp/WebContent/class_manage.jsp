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
<link rel="stylesheet" type="text/css" href="css/header.css">

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
<TABLE width=90% border=0 align=center cellPadding=5 cellSpacing=0 class="tableBorder">
	<TBODY>
	<TR>
		<Th colspan=3 align=center><B class="whitetitle">���Ź���</B></Th>
	</TR>
	<TR align=center bgcolor="#f1f3f5">
		<TD width=14%>���ű��</TD>
		<TD width=64%>��������</TD>
		<TD width=22%>����</TD>
	</TR>

	<%
	DispClass dispclass = new DispClass();
		Vector listclass = new Vector();
	listclass = dispclass.allClass();
	for(int i=0;i<listclass.size();i++) {
	IClass classinfo = (IClass)listclass.elementAt(i);%>
	<TR>
		<TD><div align="center"><%=classinfo.getID()%></div></TD>
		<TD><div align="center"><A HREF="class_edit.jsp?id=<%=classinfo.getID()%>"><%=classinfo.getName()%></A></div></TD>
		<TD align=center><A HREF="class_edit.jsp?id=<%=classinfo.getID()%>">�༭</A>
		&nbsp;<A HREF="class_del.jsp?id=<%=classinfo.getID()%>" onClick="return delpay();">ɾ��</A></TD>
	</TR>
	<%}%>
	</TBODY>
</TABLE>
