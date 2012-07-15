<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>财务管理系统</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<script Language="JavaScript">
<!--
function check_input(theForm)
{

   if (theForm.name.value == "")
  {
    alert("请输入管理员名称");
    theForm.name.focus();
    return (false);
  }

   if (theForm.pass.value == "")
  {
    alert("请输入管理员密码");
    theForm.pass.focus();
    return (false);
  }

  if (theForm.name.value.length > 20)
    {
    alert("管理员名称长度应小于20个字符");
    theForm.name.focus();
    return (false);
  }

  if (theForm.pass.value.length > 16)
    {
    alert("管理员密码长度应小于16个字符");
    theForm.pass.focus();
    return (false);
  }
   if (theForm.truename.value == "")
  {
    alert("请输入真实姓名");
    theForm.truename.focus();
    return (false);
  }
   if (theForm.classid.value == "")
  {
    alert("请选择栏目");
    theForm.classid.focus();
    return (false);
  }
     if (theForm.ipaddress.value == "")
  {
    alert("请输入用户的IP地址");
    theForm.ipaddress.focus();
    return (false);
  }

}
//-->
</script>
</head>
<body topmargin=0>

<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="user_save.jsp" onsubmit="return check_input(this)">
	<TR bgcolor="#6699CC">
		<Th colspan=2><div align="center" class="whitetitle"><B>增加用户</B></div></Th>
	</TR>
	<TR>
	  <TD width="30%" class="forumrow">&nbsp;<strong>用户名称</strong></TD>
		<TD width="70%" class="forumrow">
	  <input TYPE="text" name=name size=20 maxlength=20>&nbsp;**不得超过 10 个字符</TD>
	</TR>
	<TR>
	  <TD width="30%" class="forumrow">&nbsp;<strong>用户密码</strong></TD>
		<TD width="70%" class="forumrow">
	  <input TYPE="password" name=pass size=20 maxlength=16>
	  &nbsp;**不得超过 16 个字符</TD>
	</TR>
	<TR>
	  <TD class="forumrow">&nbsp;<strong>真实姓名</strong></TD>
	  <TD class="forumrow"><input name=truename TYPE="text" id="truename" size=20 maxlength=16>
      **不得超过 10 个字符</TD>
    </TR>
	<TR>
	  <TD width="30%" class="forumrow">&nbsp;<strong>所属部门</strong></TD>
		<TD width="70%" class="forumrow">
			<select name="classid">
			<option value="">请选择所属部门</option>
			<%	Vector listclass = new Vector();
			DispClass dispclass = new DispClass();
			listclass = dispclass.allClass();
			for(int i=0;i<listclass.size();i++) {
			IClass classinfo = (IClass)listclass.elementAt(i);%>
			<option value="<%=classinfo.getID()%>"><%=classinfo.getName()%></option>
			<%}%>
	  </select>&nbsp;**</TD>
	</TR>

	<TR>
	  <TD class="forumrow">&nbsp;<strong>限制IP</strong></TD>
      <TD class="forumrow">
        <input name=ipaddress TYPE="text" id="ipaddress" size=20 maxlength=16>
      **请输入用户IP地址</TD>
	</TR>
	<TR>
	  <TD height="45" colspan=2 align=center class="forumrow">	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="确 定"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="清 除"></FONT></TD>
	</TR>
  </form>
</TABLE>
</body>
</html>