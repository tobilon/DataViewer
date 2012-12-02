<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ include file="sessionadmin.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�Ͼ���˲�Ʒ����ƽ̨</title>

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
    { display: '����', name: 'name', width: 60, sortable: true, align: 'left' },
    { display: '�Ա�', name: 'sex', width: 30, sortable: true, align: 'left' },
    { display: '����', name: 'age', width: 30, sortable: true, align: 'left' },
    { display: '���֤��', name: 'idcard', width: 80, sortable: true, align: 'left' },
    { display: '��˾', name: 'company', width: 60, sortable: true, align: 'left' },
    { display: 'ְλ', name: 'title', width: 60, sortable: true, align: 'left' },
    { display: 'ʡ��', name: 'province', width: 60, sortable: true, align: 'left' },
    { display: '����', name: 'city', width: 60, sortable: true, align: 'left' },
    { display: '�ʱ�', name: 'zipcode', width: 80, sortable: true, align: 'left' },
    { display: '��ַ', name: 'address', width: 80, sortable: true, align: 'left' },
    { display: '�ֻ�1', name: 'mobile1', width: 100, sortable: true, align: 'left' },
    { display: '�ֻ�2', name: 'mobile2', width: 100, sortable: true, align: 'left' },
    { display: '�ֻ�3', name: 'mobile3', width: 100, sortable: true, align: 'left' },
    { display: '�ֻ�4', name: 'mobile4', width: 100, sortable: true, align: 'left' },
    { display: '�̻�1', name: 'fix1', width: 100, sortable: true, align: 'left' },
    { display: '�̻�2', name: 'fix2', width: 100, sortable: true, align: 'left' },
    { display: 'email', name: 'email', width: 100,sortable: false, align: 'left' },
    { display: '�������', name: 'category', width: 60, sortable: true, align: 'left' },
    { display: '������Դ', name: 'source', width: 80, sortable: true, align: 'left' },
    { display: '��ע', name: 'other', width: 80, sortable: true, align: 'left' },
    { display: 'ҵ��״̬', name: 'service', width: 60, sortable: true, align: 'left' }
    ],     
       
    width: 'auto', 
    height: 400, 
	usepager: true,
	title: '��ѯ���',
	findtext: '��ѯ',
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
  	  alert("��ѡ��Ҫɾ��������");
  	}
  	else
  	{
  	  if(confirm('�Ƿ�ɾ���� ' + $('.trSelected',grid).length + ' ����¼��?'))
  	  {
        var  ids ="";
        for(var i=0;i<$('.trSelected',grid).length;i++)
        {
          ids+=","+$('.trSelected',grid).find("td:first").eq(i).text();//��ȡid
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
                alert("�д�����,msg="+msg);
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
  	  alert("��ѡ��һ���޸�,����ͬʱ�޸Ķ��");
  	}
  	else if($(".trSelected").length==0)
  	{
  	  alert("��ѡ��һ����Ҫ�޸ĵļ�¼");
  	}
  }  
  
}); 


  
</script>
</head>

<body>
<ul class="topnav">
    <li><a href="main.jsp">��ҳ</a></li>
	<li>�ͻ���Դ����
		<ul class="subnav">
			<li><a href="customer_manage.jsp">�ͻ�����</a></li>
		</ul>
	</li>
	<li>ҵ��Ӫ������
		<ul class="subnav">
			<li><a href="service_manage.jsp">ҵ�����</a></li>
			<li><a href="service_add.jsp">����ҵ��</a></li>
		</ul>
	</li>
	<li>ϵͳ����
		<ul class="subnav">
			<li><a href="class_manage.jsp">���Ź���</a></li>
			<li><a href="class_add.jsp">���Ӳ���</a></li>
			<li><a href="user_manage.jsp">�û�����</a></li>
			<li><a href="user_add.jsp">�����û�</a></li>
		</ul>
	</li>
</ul>
<table id="flex1" style="display:none"></table>
<input id="action" type="hidden" name="action" value="null" />
</body>
</html>