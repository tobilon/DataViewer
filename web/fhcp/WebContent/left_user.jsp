<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="session.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<title>财务管理系统</title>
<STYLE type=text/css>
BODY {
	SCROLLBAR-FACE-COLOR: #799ae1; BACKGROUND: #799ae1; MARGIN: 0px; FONT: 12px 宋体; SCROLLBAR-HIGHLIGHT-COLOR: #799ae1; SCROLLBAR-SHADOW-COLOR: #799ae1; SCROLLBAR-3DLIGHT-COLOR: #799ae1; SCROLLBAR-ARROW-COLOR: #ffffff; SCROLLBAR-TRACK-COLOR: #aabfec; SCROLLBAR-DARKSHADOW-COLOR: #799ae1
}
TABLE {
	BORDER-RIGHT: 0px; BORDER-TOP: 0px; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px
}
TD {
	FONT: 12px 宋体
}
IMG {
	BORDER-RIGHT: 0px; BORDER-TOP: 0px; VERTICAL-ALIGN: bottom; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px
}
A {
	FONT: 12px 宋体; COLOR: #000000; TEXT-DECORATION: none
}
A:hover {
	COLOR: #428eff; TEXT-DECORATION: none
}
.sec_menu {
	BORDER-RIGHT: white 1px solid; BACKGROUND: #d6dff7; OVERFLOW: hidden; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid
}
.menu_title {
	
}
.menu_title SPAN {
	FONT-WEIGHT: bold; LEFT: 8px; COLOR: #215dc6; POSITION: relative; TOP: 2px
}
.menu_title2 {
	
}
.menu_title2 SPAN {
	FONT-WEIGHT: bold; LEFT: 8px; COLOR: #428eff; POSITION: relative; TOP: 2px
}
</STYLE>

<SCRIPT language=javascript1.2>
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}
</SCRIPT>

<META content="MSHTML 6.00.2900.2135" name=GENERATOR></HEAD>
<BODY leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=left border=0>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
        <TBODY>
        <TR>
              <TD vAlign=bottom height=42><IMG height=38 
            src="images/title.gif" width=158> </TD>
            </TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
        <TBODY>
        <TR>
              <TD class=menu_title onmouseover="this.className='menu_title2';" 
          onmouseout="this.className='menu_title';" 
          background=images/title_bg_quit.gif height=25><SPAN><A 
            href="main_user.jsp" 
            target=main><B>用户首页</B></A> | <A 
            href="logout.jsp" 
            target=_top><B>退出</B></A></SPAN> </TD>
            </TR></TBODY></TABLE>&nbsp; 
      <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
        <TBODY>
        <TR>
              <TD class=menu_title id=menuTitle1 
          onmouseover="this.className='menu_title2';" style="CURSOR: hand" 
          onclick=showsubmenu(0) onmouseout="this.className='menu_title';" 
          background=images/admin_left_2.gif height=25><SPAN>营销业务管理</SPAN> </TD>
            </TR>
        <TR>
          <TD id=submenu0 style="DISPLAY: none">
            <DIV class=sec_menu style="WIDTH: 158px">
                  <TABLE cellSpacing=0 cellPadding=0 width=150 align=center>
                    <TBODY>
                      <TR> 
                        <TD height=5></TD>
                      </TR>
                      <TR> 
                        <TD height=20><IMG height=20 alt="" 
                  src="images/bullet.gif" width=15 border=0><a 
                  href="pay_manage_user.jsp" 
                  target=main>业务管理</a></TD>
                      </TR>
                      <TR> 
                        <TD height=5></TD>
                      </TR>
                      <TR> 
                        <TD height=20><IMG height=20 alt="" 
                  src="images/bullet.gif" width=15 border=0><a 
                  href="pay_manage_user.jsp" 
                  target=main>增加业务</a></TD>
                      </TR>
                    <TBODY>
                    </TBODY>
                  </TABLE>
                </DIV>
            <DIV style="WIDTH: 158px">
            <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
              <TBODY>
              <TR>
                <TD height=20></TD></TR></TBODY></TABLE></DIV></TD></TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
        <TBODY>
        <TR>
              <TD class=menu_title id=menuTitle1 
          onmouseover="this.className='menu_title2';" style="CURSOR: hand" 
          onclick=showsubmenu(2) onmouseout="this.className='menu_title';" 
          background=images/admin_left_3.gif height=25><SPAN>系统管理</SPAN> </TD>
            </TR>
        <TR>
          <TD id=submenu2 style="DISPLAY: none">
            <DIV class=sec_menu style="WIDTH: 158px">
                  <TABLE cellSpacing=0 cellPadding=0 width=150 align=center>
                    <TBODY>
                      <TR> 
                        <TD height=5></TD>
                      </TR>
                      <TR> 
                        <TD height=20><IMG height=20 alt="" 
                  src="images/bullet.gif" width=15 border=0><A 
                  href="user_edit_user.jsp" 
                  target=main>修改密码</A></TD>
                      </TR>
                    <TBODY>
                    </TBODY>
                  </TABLE>
                </DIV>
            <DIV style="WIDTH: 158px">
            <TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
              <TBODY>
              <TR>
                <TD height=20></TD></TR></TBODY></TABLE></DIV></TD></TR></TBODY></TABLE>
        <TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
        <TBODY>
        <TR>
              <TD class=menu_title id=menuTitle1 
          onmouseover="this.className='menu_title2';" 
          onmouseout="this.className='menu_title';" 
          background=images/admin_left_9.gif height=25><SPAN>软件信息</SPAN> </TD>
            </TR>
        <TR>
          <TD>
            <DIV class=sec_menu style="WIDTH: 158px">
            <TABLE cellSpacing=0 cellPadding=0 align=center>
              <TBODY>
              <TR>
                 <TD height=40>版本：0.1.0<br>版权所有：<A 
                  href="" target=_blank>凤凰藏品</A></TD>
                      </TR></TBODY></TABLE></DIV></TD></TR></TBODY></TABLE>
        <table width="100" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><IFRAME border=0 
                  name=guest marginWidth=0 frameSpacing=0 marginHeight=0 
                  src="user_logrefresh.jsp" frameBorder=0 noResize width=100 
                  scrolling=no height=10 vspale="100"> </IFRAME></td>
          </tr>
        </table></TD>
  </TR></TBODY></TABLE></BODY></HTML>


