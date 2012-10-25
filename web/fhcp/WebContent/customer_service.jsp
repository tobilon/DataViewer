<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%int id =(null==request.getParameter("id")?1:(Integer.parseInt(request.getParameter("id"))));
CustomerControl customerCtrl = new CustomerControl();
String name = customerCtrl.getUserName(id);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title><%=name%>的业务信息</title>
<link rel="stylesheet" type="text/css" href="images/css.css">
</head>
		<h1 class='contact-title'>客户业务信息:</h1>
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;ID:&nbsp;<%=id%><br>
        &nbsp;&nbsp;&nbsp;&nbsp;姓名:&nbsp;<%=name%><br>
        <br>
		<br>
        <TABLE width=90% border=0 align=center cellPadding=5 cellSpacing=1>
			<TBODY>
				<TR align=center bgcolor="#f1f3f5">
				<TD width=15%>短信编号</TD>
				<TD width=30%>短信时间</TD>
				<TD width=45%>短信内容</TD>
				<TD width=10%>状态</TD>
				</TH>
				<%				
				DispService dispsvr = new DispService();
				ServiceControl svrCtrl = new ServiceControl();
				Vector listsvr = new Vector();
				listsvr = svrCtrl.serviceByUser(id);
				
				//分页程序开始
				int data_num=listsvr.size();
				DisPage dispage=new DisPage();

				dispage.setIntNum(5);
				int Current_Page = 0;
				String currentpage=(String)request.getParameter("currentpage");
				if (currentpage != null && !currentpage.equals(""))
				{Current_Page = Integer.parseInt(request.getParameter("currentpage"));}

				dispage.Init(Current_Page,data_num);

				int l_start = dispage.getStart();
				int l_end = dispage.getEnd();
				
				for(int i=l_start;i<l_end;i++) {
					ServiceLog serviceLog = (ServiceLog)listsvr.elementAt(i);
					Service serviceInfo = dispsvr.serviceByID(serviceLog.getServiceID());
	            	
	            %>
	            <TR>
				<TD><div align="center" class=forumRow><%=serviceLog.getServiceID()%></div></TD>
				<TD><div align="left" class=forumRow><%=serviceInfo.getSvrDate()%></div></TD>
				<TD><div align="center" class=forumRow><%=serviceInfo.getBriefSvrContent()%></div></TD>
				<TD><div align="center" class=forumRow><%=serviceLog.getServiceState()%></div></TD>
				</TR>				
				<%}%>
				<TD height="27" colspan=4 ><div align="center">记录总条数：<%=data_num%> 
	  当前页<%=Current_Page+1%>/<%=dispage.getTotalpage()%>总页数 <a href="customer_service.jsp?currentpage=0&id=<%=id%>" class="red">首页</a> <a href="customer_service.jsp?currentpage=<%=dispage.getPrepage()%>&id=<%=id%>" class="red">上一页</a> <a href="customer_service.jsp?currentpage=<%=dispage.getNextpage()%>&id=<%=id%>" class="red">下一页</a> <a href="customer_service.jsp?currentpage=<%=dispage.getTotalpage()-1%>&id=<%=id%>" class="red">末页</a> </div></TD>
	  </TR>
			</TBODY>
		</TABLE>
		<label>&nbsp;</label>