<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
	//String classname = new String(request.getParameter("classname").getBytes("ISO8859_1"),"GBK");
	String classname = new String(request.getParameter("classname"));
	int id = Integer.parseInt(request.getParameter("id"));	
	String master = (String) session.getValue("userName_s");
	ClassControl classcontrol= new ClassControl();
	classcontrol.setName(classname);
	classcontrol.setID(id);
	classcontrol.modifyclass();%>

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
	<TR height=25><Th align=center><B class="whitetitle">���ű༭�ɹ�</B></Th>
	</TR>
	<TR><TD align=center><BR>
	  ��ҳ�潫��<b><span id=yu>3</span><a href=javascript:countDown></a></b>����Զ����ز��Ź���ҳ�棬������ѡ�����²�����<BR><BR>
	<li><a href="main.jsp">������ҳ</a></li><br>
	<li><a href='class_edit.jsp?id=<%=id%>'>�����༭����</a></li><br>
	<li><a href="class_manage.jsp">���ز��Ź���ҳ��</a></li>
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