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
<TABLE width=98% border=0 align=center cellPadding=6 cellSpacing=1 class="tableBorder">
	<TBODY>
	<TR align=center bgcolor="#ffffff">
		<Th width=6%>���</Th>
		<Th width=16%>�û�����</Th>
		<Th width=15% bgcolor="#ffffff">��ʵ����</Th>
		<Th width=25% bgcolor="#ffffff">��������</Th>
		<Th width=15% bgcolor="#ffffff">����ip</Th>
		<Th width=23% bgcolor="#ffffff">����</Th>
	</TR>

	<%	
	//����û���Ϣ
	Vector listmaster = new Vector();
	listmaster = dispmaster.masterOrderID();
	for(int i=0;i<listmaster.size();i++) {
	Master masterinfo = (Master)listmaster.elementAt(i);
	
	//����û�����������Ϣ
	DispClass dispclass = new DispClass();
    int id;
	%>
	<TR >
		<TD height="27" class=forumRow><div align="center"><%=masterinfo.getID()%></div></TD>
		<TD class=forumRow><div align="center"><A HREF="user_edit.jsp?id=<%=masterinfo.getID()%>" title="����༭"><%=masterinfo.getUserName()%></A></div></TD>
		<TD class=forumRow align=center><%=masterinfo.getUserTruename()%></TD>
		<TD class=forumRow align=center><%
		    id =masterinfo.getClassid();
			
			dispclass.setID(id);
	        IClass iclass = dispclass.idToClass();if (id==0){
			out.print("�ܹ���Ա");
			}else{
			out.println(iclass.getName());
			}
	        
		%></TD>
		<TD class=forumRow align=center><%=masterinfo.getUserIpaddress()%></TD>
		<TD class=forumRow align=center><A HREF="user_edit.jsp?id=<%=masterinfo.getID()%>" title="����༭">�༭</A>&nbsp;
	  <%if (!masterinfo.getUserName().equals("admin"))	{%>|&nbsp;&nbsp;<A HREF="user_del.jsp?id=<%=masterinfo.getID()%>" title="���ɾ��" onClick="return delpay();">ɾ��</A><%}%></TD>
	</TR>
	<%}%>
	</TBODY>
</TABLE>
