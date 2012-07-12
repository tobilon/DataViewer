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
    alert("请输入管理员名称.");
    theForm.name.focus();
    return (false);
  }

   if (theForm.pass.value == "")
  {
    alert("请输入管理员密码.");
    theForm.pass.focus();
    return (false);
  }

   if (theForm.join.value == "")
  {
    alert("请输入管理员加入日期.");
    theForm.join.focus();
    return (false);
  }

   if (theForm.total.value == "")
  {
    alert("请输入管理员发新闻总数.");
    theForm.total.focus();
    return (false);
  }

   if (theForm.login.value == "")
  {
    alert("请输入管理员登陆次数.");
    theForm.login.focus();
    return (false);
  }

  if (theForm.name.value.length > 20)
    {
    alert("管理员名称长度应小于20个字符.");
    theForm.name.focus();
    return (false);
  }

  if (theForm.pass.value.length > 16)
    {
    alert("管理员密码长度应小于16个字符.");
    theForm.pass.focus();
    return (false);
  }

  if (theForm.join.value.length > 10)
    {
    alert("管理员加入日期长度应小于10个字符.");
    theForm.join.focus();
    return (false);
  }


  if (theForm.login.value.length > 10)
    {
    alert("管理员登陆次数应小于10个字符.");
    theForm.login.focus();
    return (false);
  }


   if (theForm.classid.value == "")
  {
    alert("请选择栏目.");
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
		<Th colspan=2><div align="center" class="whitetitle"><B>修改用户</B></div></Th>
	</TR>
	<TR bgcolor="f1f3f5"><TD width="30%" bgcolor="f1f3f5">&nbsp;<span class="forumrow"><strong>用户名称</strong></span></TD>
		<TD width="70%">
	  <input TYPE="text" name=name size=20 maxlength=20 value=<%=master.getUserName()%>>&nbsp;**不得超过 10 个字符</TD>
	</TR>
	<TR bgcolor="f1f3f5">
	  <TD width="30%">&nbsp;<B>用户密码</B></TD>
		<TD width="70%">
	  <input TYPE="password" name=pass size=20 maxlength=16 value=<%=master.getUserPassword()%>>
	  &nbsp;**不得超过 16 个字符</TD>
	</TR>
	<TR bgcolor="f1f3f5">
	  <TD>&nbsp;<B>真实姓名</B></TD>
	  <TD><input name=truename TYPE="text" id="truename" value="<%=master.getUserTruename()%>" size=20 maxlength=20>
      &nbsp;**不得超过 10 个字符</TD>
    </TR>
	<TR bgcolor="f1f3f5">
	  <TD><span class="forumrow">&nbsp;<strong>限制IP</strong></span></TD>
	  <TD><span class="forumrow">
	    <input name=ipaddress TYPE="text" id="ipaddress" value="<%=master.getUserIpaddress()%>" size=20 maxlength=16>
**请输入用户IP地址</span></TD>
    </TR>
	<TR bgcolor="f1f3f5"><TD width="30%">&nbsp;<B>加入日期</B></TD>
		<TD width="70%">
	  <input TYPE="text" name=join size=20 maxlength=10 value=<%=master.getJoindate()%>>&nbsp;**不得超过 10 个字符</TD>
	</TR>
	<TR bgcolor="f1f3f5"><TD width="30%">&nbsp;<span class="forumrow"><strong>所属部门</strong></span></TD>
		<TD width="70%">
			<select name="classid">
			<%
			if(master.getUserName().equals("admin")){
			out.println("<option value=\"0\">管理员</option>");
			}else{
			out.println("<option value=\"\">请选择所属部门</option>");
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
	<TR bgcolor="f1f3f5"><TD width="30%">&nbsp;<B>登陆次数</B></TD>
		<TD width="70%">
	  <input TYPE="text" name=login size=20 maxlength=10 value=<%=master.getLoginnum()%>>&nbsp;**不得超过 10 个字符</TD>
	</TR>
	<TR bgcolor="f1f3f5"><TD height="45" colspan=2 align=center>	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="确 定"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="清 除"></FONT></TD>
	</TR>
  </form>
</TABLE>
