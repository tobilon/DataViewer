<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="session.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>�������ϵͳ</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<script Language="JavaScript">
<!--
function check_input(theForm)
{

   if (theForm.pass.value == "")
  {
    alert("���������Ա����.");
    theForm.pass.focus();
    return (false);
  }


  if (theForm.pass.value.length > 16)
    {
    alert("����Ա���볤��ӦС��16���ַ�.");
    theForm.pass.focus();
    return (false);
  }

//-->
</script>
<%	  	DispMaster dispmaster = new DispMaster();
		dispmaster.setUserName(userName);
		Master master = dispmaster.nameToMaster();
%>
</head>
<body topmargin=0>

<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="user_editsave_user.jsp?id=<%=master.getID()%>" onsubmit="return check_input(this)">
	<TR height=25>
		<Th colspan=2><div align="center" class="whitetitle"><B>�޸��û�</B></div></Th>
	</TR>
	<TR bgcolor="f1f3f5">
	  <TD>&nbsp;<strong>��ʵ����</strong></TD>
	  <TD><%=master.getUserTruename()%>&nbsp;</TD>
    </TR>
	<TR bgcolor="f1f3f5">
	  <TD>&nbsp;<strong>��½��</strong></TD>
	  <TD><%=userName%>&nbsp;</TD>
    </TR>
	<TR bgcolor="f1f3f5">
	  <TD width="30%">&nbsp;<B>������</B></TD>
		<TD width="70%">
	  <input TYPE="password" name=pass size=20 maxlength=16 value=<%=master.getUserPassword()%>>
	  &nbsp;**���ó��� 16 ���ַ�</TD>
	</TR>
	<TR bgcolor="f1f3f5"><TD height="45" colspan=2 align=center>	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="ȷ ��"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="�� ��"></FONT></TD>
	</TR>
  </form>
</TABLE>
