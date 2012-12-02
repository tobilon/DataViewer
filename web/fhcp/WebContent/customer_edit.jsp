<%@ page contentType="text/html;charset=gb2312" pageEncoding="gb2312"%>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%String id = request.getParameter("id");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>批量修改用户信息</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/header.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/header.js"></script>
<script Language="JavaScript">
$.ajax({
	type: "POST",
	url: 'CustomerServlet',
	data: 'action=getusertype2',
	dataType:'html',
	success: function (data) {						
		$('.usertype').prepend(data);
	}
});
</script>
</head>
<body topmargin=0>
<TABLE width=600 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
<form method="POST" action="customer_save.jsp">
	<TR bgcolor="#6699CC">
		<Th colspan=3><div align="center" class="whitetitle"><B>批量修改客户信息</B></div></Th>
	</TR>	
	<TR>
	 <input name="id" id="id" value=<%=id%> size="10" type="hidden">
	  <TD width="20%" class="forumrow"><strong>客户备注</strong></TD>
		<TD width="60%" class="forumrow">
		<input name="extra" id="extra" size="60">
		</TD>
		<TD width= "20%" class="forumrow">
		<select name='extra_op'><option value='0' selected='selected'>替换</option><option value='1'>增加</option></select>
		</TD>
	</TR>
	<TR>
	  <TD width="20%" class="forumrow">&nbsp;<strong>数据类别</strong></TD>
		<TD width="60%" class="forumrow">
		<div class="usertype"></div>
		</TD>
		<TD width= "20%" class="forumrow">
		<select name='type_op'><option value='0' selected='selected'>替换</option><option value='1'>增加</option><option value='2'>删除</option></select>
		</TD>
	</TR>	
	<TR>
	  <TD height="45" colspan=3 align=center class="forumrow">	  <FONT color=#000000>
		<INPUT name=Submit type=submit value="确 定"> &nbsp;&nbsp;     
		<INPUT name=Submit2 type=reset value="清 除"></FONT></TD>
	</TR>
  </form>
</TABLE>
</body>
</html>