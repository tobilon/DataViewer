<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
	String name = new String(request.getParameter("name").getBytes("ISO8859_1"),"GBK");
	String pass = new String(request.getParameter("pass").getBytes("ISO8859_1"),"GBK");
	String join = new String(request.getParameter("join").getBytes("ISO8859_1"),"GBK");
	int classid = Integer.parseInt(request.getParameter("classid"));
	int login = Integer.parseInt(request.getParameter("login"));
	int id = Integer.parseInt(request.getParameter("id"));
	String truename=request.getParameter("truename");
	String ipaddress=request.getParameter("ipaddress");
	//String truename = new String(request.getParameter("truename").getBytes("ISO8859_1"),"GBK");
	//String ipaddress = new String(request.getParameter("ipaddress").getBytes("ISO8859_1"),"GBK");

	MasterControl mastercontrol = new MasterControl();
	mastercontrol.setUserName(name);
	mastercontrol.setUserPassword(pass);
	mastercontrol.setClassid(classid);
	mastercontrol.setJoindate(join);
	mastercontrol.setLoginnum(login);
	mastercontrol.setID(id);
	mastercontrol.setUserTruename(truename);
	mastercontrol.setUserIpaddress(ipaddress);
	mastercontrol.modifymaster();%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta HTTP-EQUIV=REFRESH CONTENT='4; URL=user_manage.jsp'>
<title>�Ͼ���˲�Ʒ����ƽ̨</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
	<TR height=25>
	  <Th align=center><B class="whitetitle">�޸��û���Ϣ�ɹ�</B></Th>
	</TR>
	<TR><TD align=center bgcolor="f1f3f5"><BR>
	  ��ҳ�潫��<b><span id=yu>3</span></b>����Զ������û�����ҳ�棬������ѡ�����²�����<BR>
	  <BR>
	<li><a href="main.jsp">������ҳ</a><br>
	</li>
	<br>
	<li><a href='user_edit.jsp?id=<%=id%>'>�����޸��û�</a><br>
	</li>
	<br>
	<li><a href="user_manage.jsp">�����û�����ҳ��</a><br>
	  <br>
	</li>
	</TD></TR>
</TABLE>

	<script>
	function 
	countDown(secs){yu.innerText=secs;if(--secs>0)setTimeout("countDown("+secs+")",1000);}countDown(3);
	</script>
	<%
	}
	catch(Exception e){
	out.println("������Ϣ:"+e.getMessage());
	}%>