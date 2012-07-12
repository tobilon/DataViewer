<%
String userName = (String) session.getAttribute("userName_s");
if (userName==null){ 
response.sendRedirect("error.jsp");
}
//System.out.println("<script language="+"javascript"+">window.top.location.href=login.jsp</script>");
%>