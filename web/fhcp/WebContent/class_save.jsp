<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
	//String classname = new String(request.getParameter("classname").getBytes("ISO8859_1"),"GBK");
	String classname = request.getParameter("classname");
	//String master = (String) session.getValue("userName_s");
	ClassControl classcontrol= new ClassControl();
	classcontrol.setName(classname);
	classcontrol.addclass();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta HTTP-EQUIV=REFRESH CONTENT='4; URL=class_manage.jsp'>
<title>�������ϵͳ</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
	<TR><Th align=center class="whitetitle"><strong>������ӳɹ�</strong></Th>
	</TR>
	<TR><TD align=center><BR>
	  ��ҳ�潫��<b><span id=yu>3</span><a href=javascript:countDown></a></b>����Զ����ز��Ź���ҳ�棬������ѡ�����²�����<BR>
	  <BR>
	<li><a href="main.jsp">������ҳ</a></li><br>
	<li><a href='class_add.jsp'>������Ӳ���</a></li><br>
	<li><a href="class_manage.jsp">���ع���ҳ��</a></li>
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