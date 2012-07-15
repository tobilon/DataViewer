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
<TABLE width=90% border=0 align=center cellPadding=5 cellSpacing=0 class="tableBorder">
	<TBODY>
	<TR>
		<Th colspan=3 align=center><B class="whitetitle">部门管理</B></Th>
	</TR>
	<TR align=center bgcolor="#f1f3f5">
		<TD width=14%>部门编号</TD>
		<TD width=64%>部门名称</TD>
		<TD width=22%>操作</TD>
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
		<TD align=center><A HREF="class_edit.jsp?id=<%=classinfo.getID()%>">编辑</A>
		&nbsp;<A HREF="class_del.jsp?id=<%=classinfo.getID()%>" onClick="return delpay();">删除</A></TD>
	</TR>
	<%}%>
	</TBODY>
</TABLE>
