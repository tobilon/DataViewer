<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
	String svrContent = request.getParameter("service_content");
	String svrDate = request.getParameter("service_date");
	
	ServiceControl svrcontrol = new ServiceControl();
	svrcontrol.setSvrContent(svrContent);
	svrcontrol.setSvrDate(svrDate);
	svrcontrol.addservice();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta HTTP-EQUIV=REFRESH CONTENT='4; URL=service_manage.jsp'>
<title>�������ϵͳ</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
</head>
<body topmargin=0>
<TABLE width=400 border="0" align=center cellpadding=4 cellspacing=1 class="tableBorder">
	<TR>
	  <Th height="25" align=center><B>�û���ӳɹ�</B></Th>
	</TR>
	<TR><TD align=center class="forumrow"><BR>
	  ��ҳ�潫��<b><span id=yu>3</span></b>����Զ�����ҵ�����ҳ�棬������ѡ�����²�����<BR>
	  <BR>
	<li><a href="main.jsp">������ҳ</a></li><br><br>
	<li><a href='service_add.jsp'>�������ҵ��</a></li>
	<br><br>
	<li><a href="service_manage.jsp">�����û�����ҳ��</a><br>
	  <br></li>
	</TD></TR>
</TABLE>

	<script>
	function countDown(secs)
	{
	   yu.innerText=secs;if(--secs>0)setTimeout("countDown("+secs+")",1000);
		}
	countDown(3);
	</script>
	<%
	//}//else over%>
	<%
	}
	catch(Exception e){
	out.println("������Ϣ:"+e.getMessage());
	}%>
