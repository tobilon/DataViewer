<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="session.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="refresh" content="60*30">
<title>财务管理系统</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE class=tableBorder cellSpacing=0 cellPadding=4 width=600 align=left 
border=0>
  <TBODY>
    <TR> 
      <TH class=tableHeaderText colSpan=2 height=22>凤凰藏品客户营销管理系统</TH>
    </TR> 
    
     
    <TR>
    <TD colspan="2" align=center>
    <IMG src="images/main.gif">
    </TD>
    </TR>
    
      <TR>
      <TD width="50%" height=23 align=center>&nbsp;&nbsp;&nbsp;营销业务管理：</TD>
      <TD width="50%">维护部门业务记录，分析营销数据</TD>
    </TR>
    <TR> 
      <TD width="50%" height=23 align=center>&nbsp;&nbsp;&nbsp;系统管理：</TD>
      <TD width="50%">修改用户密码</TD>
    </TR>
    
    
    <TR>
      <TD height=23>&nbsp;</TD>
      <TD>&nbsp;</TD>
    </TR>
    
    <TR>
    <TD colspan="2" align=center>登录用户：<font class=red><%=userName%></font>&nbsp;&nbsp;&nbsp;
                                                                                                               部门： <font class=red><%
        DispMaster dispmaster = new DispMaster();
	    dispmaster.setUserName(userName);
	    Master master = dispmaster.nameToMaster();
	    DispClass dispClass = new DispClass();
		dispClass.setID(master.getClassid());
		IClass iclass = dispClass.idToClass();
		out.println(iclass.getName());
        %></font>
    </TD>
    </TR>
  </TBODY>
</TABLE>
<br>
<P></P>
</BODY></HTML>
