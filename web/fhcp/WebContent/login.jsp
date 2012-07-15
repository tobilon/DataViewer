<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%
//String userName = (String) session.getAttribute("userName_s");
//if (userName!=null){ 
//response.sendRedirect("index.jsp");
//}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<title>南京凤凰藏品数据平台</title>
<link rel="stylesheet" href="images/css.css" type="text/css" media="screen">
<script Language="JavaScript">
<!--
function isspacestring(mystring)
{ var istring=mystring;
  var temp,i,strlen;
  temp=true;
  strlen=istring.length;
  for (i=0;i<strlen;i++)
  {
    if ((istring.substring(i,i+1)!=" ")&(temp))
     { temp=false;  }
  }
 return temp;
}

function firstisspace(mystring)
{ var istring=mystring;
  var temp,i,strlen;
  temp=false;
    if (istring.substring(0,1)==" ")
     { temp=true;  }
 return temp;
}

function check_input(theForm)
{

   if ((theForm.username.value == "")|(firstisspace(theForm.username.value)))
  {
    alert("请输入用户名.不能以空格开头");
    theForm.username.focus();
    return (false);
  }

  if ((theForm.password.value == "")|(isspacestring(theForm.password.value)))
  {
    alert("请输入密码.");
    theForm.password.focus();
    return (false);
  }
}
//-->
</script>
</head>
<body>
<p>&nbsp;</p><FORM action=chklogin.jsp method=post onsubmit="return check_input(this)">
<table width="400" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#6685C5">

  <tr>
    <td bgcolor="#FFFFFF"><table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="3"><img src="images/login.gif" width="400" height="167"></td>
      </tr>
      <tr>
        <td height="35" colspan="3">
        <div align="center">
                           用户：
        <INPUT name=username class="s01" size=16 maxLength=16>
                           密码：
        <INPUT name=password type=password class="s01" size=16 maxLength=20>
        </div>
        </td>
      </tr>
      <tr>
        <td width="239" height="37"><div align="center">版权所有：数据开发二人组 &nbsp;</div></td>
        <td width="21">&nbsp;            </td>
        <td width="140">
          <input name=submit type=submit class="s02" value="登 陆">
          <input name=submit1 type=reset class="s02" id="submit1" value="取 消"></td>
      </tr>
	 
    </table></td>
  </tr>
</table> </FORM>
</body></html>