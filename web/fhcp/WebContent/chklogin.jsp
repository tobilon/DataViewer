<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<title>�Ͼ���˲�Ʒ����ƽ̨</title>
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
	loginerror = "�����������û���!";
}
if("".equals(passWord)) {
	loginerror = "��������������!";
}
	DispMaster dispmaster = new DispMaster();
	dispmaster.setUserName(userName);
	dispmaster.setUserPassword(passWord);
	if(dispmaster.chkLogin() == false){
		loginerror = "�û�����������������µ�½��";
%>

	<TABLE width=400 border="0" align=center cellpadding=0 cellspacing=1 class="tableBorder">
      <TR bgcolor=#336699 >
        <Th align=center bgcolor="#336699" height=25><B>����</B></th>
      </TR>
      <TR>
        <TD align=center class="forumrow"><p><BR>
            <%=loginerror%><BR>
            <BR>
            <A href="javascript:onclick=history.back()">[������ҳ]</A><BR>
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
	
	//�ڵ�½��Ϣ���һ����¼
	LogControl logcontrol =new LogControl();
	
	String ip=request.getRemoteAddr();//��õ�½��Աip��ַ

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