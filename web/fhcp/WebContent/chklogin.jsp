<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<title>南京凤凰藏品数据平台</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body>
<%
String loginerror = null;
String userName = request.getParameter("username");
String passWord = request.getParameter("password");
%>
	<%
if("".equals(userName)) {
	loginerror = "请输入您的用户名!";
}
if("".equals(passWord)) {
	loginerror = "请输入您的密码!";
}
	DispMaster dispmaster = new DispMaster();
	dispmaster.setUserName(userName);
	dispmaster.setUserPassword(passWord);
	if(dispmaster.chkLogin() == false){
		loginerror = "用户名或密码错误！请重新登陆。";
%>

	<TABLE width=400 border="0" align=center cellpadding=0 cellspacing=1 class="tableBorder">
      <TR bgcolor=#336699 >
        <Th align=center bgcolor="#336699" height=25><B>错误</B></th>
      </TR>
      <TR>
        <TD align=center class="forumrow"><p><BR>
            <%=loginerror%><BR>
            <BR>
            <A href="javascript:onclick=history.back()">[返回上页]</A><BR>
          </p>
        </TD>
      </TR>
    </TABLE>
    <%}
	else{
	String userName_s = null;
	String passWord_s = null;
	session.setAttribute("userName_s",userName);
	session.setAttribute("passWord_s",passWord);
	MasterControl mastercontrol = new MasterControl();
	mastercontrol.setUserName(userName);
	mastercontrol.addloginnum();
	
	//在登陆信息里加一条记录
	LogControl logcontrol =new LogControl();
	
	String ip=request.getRemoteAddr();//获得登陆人员ip地址

	logcontrol.setUser(userName);
	logcontrol.setIp(ip);
	
	logcontrol.addlogin();
	if (userName.equals("admin")){ 
	    response.sendRedirect("index.jsp");
    }
	else{
	    response.sendRedirect("index_user.jsp");
	}
	
	}
%>