<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%int id =(null==request.getParameter("id")?1:(Integer.parseInt(request.getParameter("id"))));
		DispClass dispclass = new DispClass();
		dispclass.setID(id);
		IClass iclass = dispclass.idToClass();	%>
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
<TABLE width=50% border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="class_editsave.jsp?id=<%=id%>" onsubmit="return check_input(this)">
	<TR height=25>
		<Th colspan=2><div align="center" class="whitetitle"><B>�༭����</B></div></Th>
	</TR>
	<TR bgcolor="#FFFFFF"><TD width="30%">&nbsp;<B>��������</B></TD>
		<TD width="70%">
	  <input TYPE="text" name=classname size=20 maxlength=20 value=<%=iclass.getName()%>>&nbsp;**���ó��� 10 ������</TD>
	</TR>

	<TR bgcolor="#FFFFFF"><TD colspan=2 align=center><BR>
	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="ȷ ��"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="�� ��"></FONT><BR></TD>
	</TR>
  </form>
</TABLE>