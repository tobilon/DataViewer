<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
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

   if (theForm.name.value == "")
  {
    alert("���������Ա����.");
    theForm.name.focus();
    return (false);
  }

   if (theForm.pass.value == "")
  {
    alert("���������Ա����.");
    theForm.pass.focus();
    return (false);
  }

   if (theForm.join.value == "")
  {
    alert("���������Ա��������.");
    theForm.join.focus();
    return (false);
  }

   if (theForm.total.value == "")
  {
    alert("���������Ա����������.");
    theForm.total.focus();
    return (false);
  }

   if (theForm.login.value == "")
  {
    alert("���������Ա��½����.");
    theForm.login.focus();
    return (false);
  }

  if (theForm.name.value.length > 20)
    {
    alert("����Ա���Ƴ���ӦС��20���ַ�.");
    theForm.name.focus();
    return (false);
  }

  if (theForm.pass.value.length > 16)
    {
    alert("����Ա���볤��ӦС��16���ַ�.");
    theForm.pass.focus();
    return (false);
  }

  if (theForm.join.value.length > 10)
    {
    alert("����Ա�������ڳ���ӦС��10���ַ�.");
    theForm.join.focus();
    return (false);
  }


  if (theForm.login.value.length > 10)
    {
    alert("����Ա��½����ӦС��10���ַ�.");
    theForm.login.focus();
    return (false);
  }


   if (theForm.classid.value == "")
  {
    alert("��ѡ����Ŀ.");
    theForm.classid.focus();
    return (false);
  }

}
//-->
</script>
<%int id =(null==request.getParameter("id")?1:(Integer.parseInt(request.getParameter("id"))));
		DispMaster dispmaster = new DispMaster();
		dispmaster.setID(id);
		Master master = dispmaster.idToMaster();	%>
</head>
<body topmargin=0>

<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="user_editsave.jsp?id=<%=id%>" onsubmit="return check_input(this)">
	<TR height=25>
		<Th colspan=2><div align="center" class="whitetitle"><B>�޸��û�</B></div></Th>
	</TR>
	<TR bgcolor="f1f3f5"><TD width="30%" bgcolor="f1f3f5">&nbsp;<span class="forumrow"><strong>�û�����</strong></span></TD>
		<TD width="70%">
	  <input TYPE="text" name=name size=20 maxlength=20 value=<%=master.getUserName()%>>&nbsp;**���ó��� 10 ���ַ�</TD>
	</TR>
	<TR bgcolor="f1f3f5">
	  <TD width="30%">&nbsp;<B>�û�����</B></TD>
		<TD width="70%">
	  <input TYPE="password" name=pass size=20 maxlength=16 value=<%=master.getUserPassword()%>>
	  &nbsp;**���ó��� 16 ���ַ�</TD>
	</TR>
	<TR bgcolor="f1f3f5">
	  <TD>&nbsp;<B>��ʵ����</B></TD>
	  <TD><input name=truename TYPE="text" id="truename" value="<%=master.getUserTruename()%>" size=20 maxlength=20>
      &nbsp;**���ó��� 10 ���ַ�</TD>
    </TR>
	<TR bgcolor="f1f3f5">
	  <TD><span class="forumrow">&nbsp;<strong>����IP</strong></span></TD>
	  <TD><span class="forumrow">
	    <input name=ipaddress TYPE="text" id="ipaddress" value="<%=master.getUserIpaddress()%>" size=20 maxlength=16>
**�������û�IP��ַ</span></TD>
    </TR>
	<TR bgcolor="f1f3f5"><TD width="30%">&nbsp;<B>��������</B></TD>
		<TD width="70%">
	  <input TYPE="text" name=join size=20 maxlength=10 value=<%=master.getJoindate()%>>&nbsp;**���ó��� 10 ���ַ�</TD>
	</TR>
	<TR bgcolor="f1f3f5"><TD width="30%">&nbsp;<span class="forumrow"><strong>��������</strong></span></TD>
		<TD width="70%">
			<select name="classid">
			<%
			if(master.getUserName().equals("admin")){
			out.println("<option value=\"0\">����Ա</option>");
			}else{
			out.println("<option value=\"\">��ѡ����������</option>");
			}
			%>
			<%	Vector listclass = new Vector();
			DispClass dispclass = new DispClass();
			listclass = dispclass.allClass();
			for(int i=0;i<listclass.size();i++) {
			IClass classinfo = (IClass)listclass.elementAt(i);
			if (master.getClassid()==classinfo.getID()){%>
			<option value="<%=classinfo.getID()%>" selected><%=classinfo.getName()%></option><%} else{%>
			<option value="<%=classinfo.getID()%>"><%=classinfo.getName()%></option>
			<%}}%>
	  </select>&nbsp;**</TD>
	</TR>
	<TR bgcolor="f1f3f5"><TD width="30%">&nbsp;<B>��½����</B></TD>
		<TD width="70%">
	  <input TYPE="text" name=login size=20 maxlength=10 value=<%=master.getLoginnum()%>>&nbsp;**���ó��� 10 ���ַ�</TD>
	</TR>
	<TR bgcolor="f1f3f5"><TD height="45" colspan=2 align=center>	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="ȷ ��"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="�� ��"></FONT></TD>
	</TR>
  </form>
</TABLE>
