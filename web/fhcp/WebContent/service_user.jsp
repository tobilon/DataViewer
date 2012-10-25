<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%int id =(null==request.getParameter("id")?1:(Integer.parseInt(request.getParameter("id"))));
String state = request.getParameter("userstate");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>短信编号<%=id%>的客户列表</title>
<link rel="stylesheet" type="text/css" href="images/css.css">
<script>
function userfilter(id)
{
	alert(id);
	alert(state);
	var userstate = document.getElementById("userstate").value;
	window.location.href="service_user.jsp?id="+id+"&userstate="+userstate;
}
</script>
</head>
		<h1 class='contact-title'>客户信息:</h1>
		<br>
		<br>
        <TABLE width=90% border=0 align=center cellPadding=5 cellSpacing=1>
			<TBODY>
			    <TR><TD height="27" colspan=7 align="right">
			    <select name='userstate' id='userstate'><option value='' selected='selected'>不限</option><option value='0'>未处理</option><option value='1'>待定</option><option value='2'>已失败</option><option value='3'>已响应</option><option value='4'>已订购</option></select>
			    <input name='userquery' type='button' value='按状态过滤' onclick='userfilter()'></input>
			    </TD></TR>
				<TR align=center bgcolor="#f1f3f5">
				<TD width=15%>用户ID</TD>
				<TD width=15%>用户名</TD>
				<TD width=10%>手机1</TD>
				<TD width=10%>手机2</TD>
				<TD width=10%>手机3</TD>
				<TD width=10%>手机4</TD>
				<TD width=10%>状态</TD>
				</TH>
				<%				
				ServiceControl svrCtrl = new ServiceControl();
				Vector listuser = new Vector();
				listuser = svrCtrl.getServiceUserID(id, state);
				
				//分页程序开始
				int data_num=listuser.size();
				DisPage dispage=new DisPage();

				dispage.setIntNum(20);
				int Current_Page = 0;
				String currentpage=(String)request.getParameter("currentpage");
				if (currentpage != null && !currentpage.equals(""))
				{Current_Page = Integer.parseInt(request.getParameter("currentpage"));}

				dispage.Init(Current_Page,data_num);

				int l_start = dispage.getStart();
				int l_end = dispage.getEnd();
				
				for(int i=l_start;i<l_end;i++) {
					ServiceLog serviceLog = (ServiceLog)listuser.elementAt(i);	
					CustomerControl customerCtrl = new CustomerControl();
					customerCtrl.getUserInfo(serviceLog.getUserID());
	            %>
	            <TR>
				<TD><div align="center" class=forumRow><%=serviceLog.getUserID()%></div></TD>
				<TD><div align="left" class=forumRow><%=customerCtrl.getName()%></div></TD>
				<TD><div align="center" class=forumRow><%=customerCtrl.getMobile1()%></div></TD>
				<TD><div align="center" class=forumRow><%=customerCtrl.getMobile2()%></div></TD>
				<TD><div align="center" class=forumRow><%=customerCtrl.getMobile3()%></div></TD>
				<TD><div align="center" class=forumRow><%=customerCtrl.getMobile4()%></div></TD>
				<TD><div align="center" class=forumRow><%=customerCtrl.getServState()%></div></TD>
				</TR>				
				<%}%>
				<TR>
				<TD height="27" colspan=7 ><div align="center">记录总条数：<%=data_num%> 
	  当前页<%=Current_Page+1%>/<%=dispage.getTotalpage()%>总页数 <a href="service_user.jsp?currentpage=0&id=<%=id%>" class="red">首页</a> <a href="service_user.jsp?currentpage=<%=dispage.getPrepage()%>&id=<%=id%>" class="red">上一页</a> <a href="service_user.jsp?currentpage=<%=dispage.getNextpage()%>&id=<%=id%>" class="red">下一页</a> <a href="service_user.jsp?currentpage=<%=dispage.getTotalpage()-1%>&id=<%=id%>" class="red">末页</a> </div></TD>
	  </TR>
			</TBODY>
		</TABLE>
		<label>&nbsp;</label>