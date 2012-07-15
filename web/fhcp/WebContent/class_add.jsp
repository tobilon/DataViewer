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
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen"><script Language="JavaScript">
<!--
function check_input(theForm)
{

   if (theForm.classname.value == "")
  {
    alert("请输入部门名称.");
    theForm.classname.focus();
    return (false);
  }

  if (theForm.classname.value.length > 20)
    {
    alert("部门名称长度应小于10个字符或数字.");
    theForm.classname.focus();
    return (false);
  }

}
//-->
</script>
</head>
<body topmargin=0>
<TABLE width=50% border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="class_save.jsp" onsubmit="return check_input(this)">
	<TR>
		<Th colspan=2><div align="center">添加部门</span></div></th>
	</TR>
	<TR bgcolor="#FFFFFF">
	  <TD width="30%">部门名称</TD>
		<TD width="70%">
	  <input TYPE="text" name=classname size=20 maxlength=20>&nbsp;**不得超过 10 个汉字</TD>
	</TR>

	<TR bgcolor="#FFFFFF"><TD height="45" colspan=2 align=center>	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="确 定"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="清 除"></FONT></TD>
	</TR>
  </form>
</TABLE>


