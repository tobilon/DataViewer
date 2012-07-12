<%
String userName = (String) session.getAttribute("userName_s");
if(userName==null){ 
   response.sendRedirect("error1.jsp");
   }
else{
   if(!userName.equals("admin")){ 
	response.sendRedirect("error1.jsp");
	}
}
//System.out.println("<script language="+"javascript"+">window.top.location.href=login.jsp</script>");
%>