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
<title>�Ͼ���˲�Ʒ����ƽ̨</title>
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
    alert("�������û���.�����Կո�ͷ");
    theForm.username.focus();
    return (false);
  }

  if ((theForm.password.value == "")|(isspacestring(theForm.password.value)))
  {
    alert("����������.");
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
                           �û���
        <INPUT name=username class="s01" size=16 maxLength=16>
                           ���룺
        <INPUT name=password type=password class="s01" size=16 maxLength=20>
        </div>
        </td>
      </tr>
      <tr>
        <td width="239" height="37"><div align="center">��Ȩ���У����ݿ��������� &nbsp;</div></td>
        <td width="21">&nbsp;            </td>
        <td width="140">
          <input name=submit type=submit class="s02" value="�� ½">
          <input name=submit1 type=reset class="s02" id="submit1" value="ȡ ��"></td>
      </tr>
	 
    </table></td>
  </tr>
</table> </FORM>
</body></html>