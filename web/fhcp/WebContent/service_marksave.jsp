<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<%try{
	String id = new String(request.getParameter("id").getBytes("ISO8859_1"),"GBK");
	String numlist = new String(request.getParameter("number_list").getBytes("ISO8859_1"),"GBK");
	String marktype = new String(request.getParameter("mark_type").getBytes("ISO8859_1"),"GBK");
	
	ServiceControl svrCtrl = new ServiceControl();
	String s[] = numlist.split("\r\n");
	
	
	for(int i = 0; i < s.length; i ++)
	{
		if(marktype.equals("user_send"))
		{
			svrCtrl.addservicelog(s[i], Long.parseLong(id));
		}
		else if(marktype.equals("user_ack"))
		{
			svrCtrl.updateLogAck(s[i], Long.parseLong(id));
		}
		else
		{
			svrCtrl.updateLogDeal(s[i], Long.parseLong(id));
		}
	}
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
	<TR height=25>
	  <Th align=center><B class="whitetitle">����û��ɹ�</B></Th>
	</TR>
	<TR><TD align=center bgcolor="f1f3f5"><BR>
	  ��ҳ�潫��<b><span id=yu>3</span></b>����Զ������û�����ҳ�棬������ѡ�����²�����<BR>
	  <BR>
	<li><a href="main.jsp">������ҳ</a><br>
	</li>
	<br>
	<li><a href='service_mark.jsp?id=<%=id%>'>��������û�</a><br>
	</li>
	<br>
	<li><a href="service_manage.jsp">����ҵ�����ҳ��</a><br>
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