<%@ page contentType="text/html;charset=gb2312" %>
<%
String userName = (String) session.getValue("userName_s");
session.removeValue("userName_s");
session.removeValue("userName");
//response.sendRedirect("login.jsp");
%>
<script language="javascript">window.top.location.href='login.jsp'</script>