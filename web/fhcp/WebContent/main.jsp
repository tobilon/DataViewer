<%@ page contentType="text/html; charset=gb2312"  pageEncoding="gb2312"%>
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
<TABLE class=tableBorder cellSpacing=0 cellPadding=4 width=600 align=center 
border=0>
  <TBODY>
    <TR> 
      <TH class=tableHeaderText colSpan=2 height=22>��˲�Ʒ�ͻ�Ӫ������ϵͳ</TH>
    </TR>  
    <TR>
    <TD colspan="2" align=center>
    <IMG src="images/main.gif">
    </TD>
    </TR>
    <TR> 
      <TD width="50%" height=23 align=center>&nbsp;&nbsp;&nbsp;�ͻ���Դ����</TD>
      <TD width="50%">ά���ͻ������Ϣ����ϵ��ʽ</TD>
    </TR>
    <TR>
      <TD width="50%" height=23 align=center>&nbsp;&nbsp;&nbsp;Ӫ��ҵ�����</TD>
      <TD width="50%">ά��ҵ���¼������Ӫ������</TD>
    </TR>
    <TR> 
      <TD width="50%" height=23 align=center>&nbsp;&nbsp;&nbsp;ϵͳ����</TD>
      <TD width="50%">��������û���������Ϣ</TD>
    </TR>
    <TR>
      <TD height=23>&nbsp;</TD>
      <TD><div align="right"></div></TD>
    </TR>
  </TBODY>
</TABLE>
<P></P>
</BODY></HTML>
