<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>南京凤凰藏品数据平台</title>

<link rel="stylesheet" type="text/css" href="images/css.css">
<link rel="stylesheet" type="text/css" href="css/flexigrid.pack.css">
<link rel="stylesheet" type="text/css" href="css/contact.css">
<link rel="stylesheet" type="text/css" href="css/header.css">

<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="js/flexigrid.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/header.js"></script>


<script type="text/javascript">

$("document").ready(function() { 	
	
  /* flexigrid definition begin */
  $('#flex1').flexigrid({
    url:'CustomerServlet',
    dataType:'json',   			
    colModel:[
    { display: 'ID', name: 'id', width: 80, sortable: true, align: 'left' },
    { display: '姓名', name: 'name', width: 60, sortable: true, align: 'left' },
    { display: '性别', name: 'sex', width: 30, sortable: true, align: 'left' },
    { display: '年龄', name: 'age', width: 30, sortable: true, align: 'left' },
    { display: '身份证号', name: 'idcard', width: 80, sortable: true, align: 'left' },
    { display: '公司', name: 'company', width: 60, sortable: true, align: 'left' },
    { display: '职位', name: 'title', width: 60, sortable: true, align: 'left' },
    { display: '省份', name: 'province', width: 60, sortable: true, align: 'left' },
    { display: '城市', name: 'city', width: 60, sortable: true, align: 'left' },
    { display: '邮编', name: 'zipcode', width: 80, sortable: true, align: 'left' },
    { display: '地址', name: 'address', width: 80, sortable: true, align: 'left' },
    { display: '手机1', name: 'mobile1', width: 100, sortable: true, align: 'left' },
    { display: '手机2', name: 'mobile2', width: 100, sortable: true, align: 'left' },
    { display: '手机3', name: 'mobile3', width: 100, sortable: true, align: 'left' },
    { display: '手机4', name: 'mobile4', width: 100, sortable: true, align: 'left' },
    { display: '固话1', name: 'fix1', width: 100, sortable: true, align: 'left' },
    { display: '固话2', name: 'fix2', width: 100, sortable: true, align: 'left' },
    { display: 'email', name: 'email', width: 100,sortable: false, align: 'left' },
    { display: '数据类别', name: 'category', width: 60, sortable: true, align: 'left' },
    { display: '数据来源', name: 'source', width: 80, sortable: true, align: 'left' },
    { display: '备注', name: 'other', width: 80, sortable: true, align: 'left' },
    { display: '业务状态', name: 'service', width: 60, sortable: true, align: 'left' }
    ],     
       
    width: 'auto', 
    height: 400, 
	usepager: true,
	title: '查询结果',
	findtext: '查询',
	rp: 15,
	rpOptions: [10, 15, 20, 30, 40, 100],
	autoload: true,
	resizable: false,
	hideOnSubmit: true,
	blockOpacity: 0.5,
	showcheckbox: true});
	/* flexigrid definition end */

  /* insert customer data begin */
  function onAddCustomer(com, grid)
  {
  	  alert("Add Customer");  	
  }
  /* insert customer data end */

  /* delete customer data begin */
  function onDelCustomer(com, grid)
  {
	$("#action").value="delete";
  	if($('.trSelected',grid).length==0)
  	{
  	  alert("请选择要删除的数据");
  	}
  	else
  	{
  	  if(confirm('是否删除这 ' + $('.trSelected',grid).length + ' 条记录吗?'))
  	  {
        var  ids ="";
        for(var i=0;i<$('.trSelected',grid).length;i++)
        {
          ids+=","+$('.trSelected',grid).find("td:first").eq(i).text();//获取id
        }
        ids=ids.substring(1);
        $.ajax({
        	type: "POST",
        	url: "CustomerServlet?action="+${"action"}.value,
            data: "id="+ids,
            dataType:"text",
            success: function(msg){
              if(msg=="success")
              {
            	$("#flex1").flexReload();
              }
              else
              {
                alert("有错误发生,msg="+msg);
              }
            },
  	        error: function(msg){
  	          alert(msg);
  	        }
        });
      }
    }
  }
  	
  function onModCustomer(com, grid)
  {
    $("#action").value="modify";
  	if($(".trSelected").length==1)
  	{
  	  $.get("customer_edit.jsp", function(data){
  		  // create a modal dialog with the data
  	    $(data).modal({
  	      closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
  	      position: ["15%",],
          overlayId: 'contact-overlay',
          containerId: 'contact-container',
          onOpen: open,
          onShow: show,
          onClose: close
          });
  		  
  		  		  
  	    $("#customer_id").attr("value",$('.trSelected',grid).find("td").eq(0).text());
  		$("#customer_name").attr("value",$('.trSelected',grid).find("td").eq(1).text());
  	    $("#customer_sex").attr("value",$('.trSelected',grid).find("td").eq(2).text());
  	    $("#customer_age").attr("value",$('.trSelected',grid).find("td").eq(3).text());
  	    $("#customer_job").attr("value",$('.trSelected',grid).find("td").eq(4).text());
  	    $("#customer_province").attr("value",$('.trSelected',grid).find("td").eq(5).text());
  	    $("#customer_city").attr("value",$('.trSelected',grid).find("td").eq(6).text());
  	    $("#customer_post").attr("value",$('.trSelected',grid).find("td").eq(7).text());
  	    $("#customer_mobile").attr("value",$('.trSelected',grid).find("td").eq(8).text());
  	    $("#customer_fix").attr("value",$('.trSelected',grid).find("td").eq(9).text());
  	    $("#customer_mail").attr("value",$('.trSelected',grid).find("td").eq(10).text());
  	    $("#customer_class").attr("value",$('.trSelected',grid).find("td").eq(11).text());
  	    $("#customer_source").attr("value",$('.trSelected',grid).find("td").eq(12).text());
  	    $("#customer_other").attr("value",$('.trSelected',grid).find("td").eq(13).text());
      });  	  
  	}
  	else if($(".trSelected").length>1)
  	{
  	  alert("请选择一个修改,不能同时修改多个");
  	}
  	else if($(".trSelected").length==0)
  	{
  	  alert("请选择一个您要修改的记录");
  	}
  }  
  
}); 


  
</script>
</head>

<body>
<ul class="topnav">
    <li><a href="main.jsp">首页</a></li>
	<li>客户资源管理
		<ul class="subnav">
			<li><a href="customer_manage.jsp">客户管理</a></li>
		</ul>
	</li>
	<li>业务营销管理
		<ul class="subnav">
			<li><a href="service_manage.jsp">业务管理</a></li>
			<li><a href="service_add.jsp">新增业务</a></li>
		</ul>
	</li>
	<li>系统管理
		<ul class="subnav">
			<li><a href="class_manage.jsp">部门管理</a></li>
			<li><a href="class_add.jsp">增加部门</a></li>
			<li><a href="user_manage.jsp">用户管理</a></li>
			<li><a href="user_add.jsp">增加用户</a></li>
		</ul>
	</li>
</ul>
<table id="flex1" style="display:none"></table>
<input id="action" type="hidden" name="action" value="null" />
</body>
</html>