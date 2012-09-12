<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务管理系统</title>

<link rel="stylesheet" type="text/css" href="images/css.css">
<link rel="stylesheet" type="text/css" href="css/flexigrid.pack.css">
<link rel="stylesheet" type="text/css" href="css/contact.css">

<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="js/flexigrid.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>


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
    { display: '职位', name: 'title', width: 60, sortable: true, align: 'left' },
    { display: '省份', name: 'province', width: 60, sortable: true, align: 'left' },
    { display: '城市', name: 'city', width: 60, sortable: true, align: 'left' },
    { display: '邮编', name: 'zipcode', width: 80, sortable: true, align: 'left' },
    { display: '手机', name: 'mobile', width: 100, sortable: true, align: 'left' },
    { display: '固话', name: 'fix', width: 100, sortable: true, align: 'left' },
    { display: 'email', name: 'email', width: 100,sortable: false, align: 'left' },
    { display: '类别', name: 'category', width: 60, sortable: true, align: 'left' },
    { display: '数据来源', name: 'source', width: 80, sortable: true, align: 'left' },
    { display: '备注', name: 'other', width: 80, sortable: true, align: 'left' },
    { display: '业务信息', name: 'service', width: 60, sortable: true, align: 'left' }
    ],     
    searchitems : [
                {display: 'ID', name : 'id', isdefault: true},
                {display: '姓名', name : 'name'},
                {display: '手机', name : 'mobilephone1'},
                {display: '邮箱', name : 'mail'}
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

function validate()
{
	return true;
}

function show(dialog)
{
  $('#contact-container .contact-send').click(function(e){	  
	  
  e.preventDefault();
  // 提交用户数据变更
  if(validate())
  {
    var msg = $('#contact-container .contact-message');
    msg.fadeOut(function () {
      msg.removeClass('contact-error').empty();
    });
    
    $('#contact-container .contact-title').html('Sending...');
    $('#contact-container form').fadeOut(200);   
    $('#contact-container .contact-content').animate({
      height: '80px'
      },function (){
        $('#contact-container .contact-loading').fadeIn(200, function () {
        	$.ajax({
        	  	  url: 'CustomerServlet',
        	  	  data: $('#contact-container form').serialize() + '&action=modify',        	  	  
        	  	  type: 'post',
        	  	  cache: false,
        	  	  dataType: 'html',
        	  	  success: function (data) {
        	          $('#contact-container .contact-loading').fadeOut(200, function () {
        	            $('#contact-container .contact-title').html('提交成功!');
        	            msg.html(data).fadeIn(200);
        	            $("#flex1").flexReload();
        	            });
        	          },
        	        error: error});
          });
        });
  }
  else
  {
	if ($('#contact-container .contact-message:visible').length > 0)
	{
      var msg = $('#contact-container .contact-message div');
      msg.fadeOut(200, function () {
    	  msg.empty();
    	  contact.showError();
    	  msg.fadeIn(200);
    	  });
    }
	else
	{
		$('#contact-container .contact-message').animate({
					height: '30px'
				}, contact.showError);
			}
			
		}
	});	
}

function open(dialog)
{
	// dynamically determine height
	var h = 500;	
	var title = $('#contact-container .contact-title').html();
	$('#contact-container .contact-title').html('Loading...');
	dialog.overlay.fadeIn(200, function () {
      dialog.container.fadeIn(200, function () {
        dialog.data.fadeIn(200, function () {
          $('#contact-container .contact-content').animate({
            height: h}, function () {
              $('#contact-container .contact-title').html(title);
              $('#contact-container form').fadeIn(200, function () {
                });
              });
          });
        });
     });
}
	
function close(dialog) {
	$('#contact-container .contact-message').fadeOut();
	$('#contact-container form').fadeOut(200);
	$('#contact-container .contact-content').animate({
      height: 40}, function () {
        dialog.data.fadeOut(200, function () {
          dialog.container.fadeOut(200, function () {
            dialog.overlay.fadeOut(200, function () {
              $.modal.close();
              });
            });
          });
        });
	}
	
function error (xhr) {
	alert(xhr.statusText);
}

function showerror() {
	$('#contact-container .contact-message')
		.html($('<div class="contact-error"></div>').append(contact.message))
		.fadeIn(200);
}

  
</script>
</head>

<body>
<table id="flex1" style="display:none"></table>
<input id="action" type="hidden" name="action" value="null" />
</body>
</html>