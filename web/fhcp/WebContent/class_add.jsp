<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>�Ͼ���˲�Ʒ����ƽ̨</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="css/header.css">

<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/header.js"></script>
<script Language="JavaScript">
<!--
function check_input(theForm)
{

   if (theForm.classname.value == "")
  {
    alert("�����벿������.");
    theForm.classname.focus();
    return (false);
  }

  if (theForm.classname.value.length > 20)
    {
    alert("�������Ƴ���ӦС��10���ַ�������.");
    theForm.classname.focus();
    return (false);
  }

}
//-->
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
<TABLE width=50% border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="class_save.jsp" onsubmit="return check_input(this)">
	<TR>
		<Th colspan=2><div align="center">��Ӳ���</span></div></th>
	</TR>
	<TR bgcolor="#FFFFFF">
	  <TD width="30%">��������</TD>
		<TD width="70%">
	  <input TYPE="text" name=classname size=20 maxlength=20>&nbsp;**���ó��� 10 ������</TD>
	</TR>

	<TR bgcolor="#FFFFFF"><TD height="45" colspan=2 align=center>	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="ȷ ��"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="�� ��"></FONT></TD>
	</TR>
  </form>
</TABLE>


