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
}
//-->
</script>
</head>
<body topmargin=0>
<TABLE width=50% border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="service_marksave.jsp?id=<%=id%>" onsubmit="return check_input(this)">
	<TR height=25>
		<Th colspan=2><div align="center" class="whitetitle"><B>�û����</B></div></Th>
	</TR>
	<TR><TD width="30%"  class=forumRow>&nbsp;<B>�����б�</B></TD>
		<TD width="70%"  class=forumRow>
		<textarea  name=number_list rows=10 cols=60></textarea><br></TD>
	</TR>
	<TR><TD width="30%"  class=forumRow>&nbsp;<B>�������</B></TD>
	    <TD width="70%" class=forumRow><BR>
	    <input type='radio' name='mark_type' checked='checked' value='user_send'/>&nbsp;�����û�
	    <input type='radio' name='mark_type' value='user_ack'/>&nbsp;��Ӧ�û�
	    <input type='radio' name='mark_type' value='user_deal'/>&nbsp;�����û�</TD>
	</TR>

	<TR><TD colspan=2 align=center  class=forumRow><BR>
	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="ȷ ��"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="�� ��"></FONT><BR></TD>
	</TR>
  </form>
</TABLE>