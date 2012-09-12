<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div style='display:none'>
	<div class='contact-top'></div>
	<div class='contact-content'>
		<h1 class='contact-title'>修改客户信息:</h1>
		<div class='contact-loading' style='display:none'></div>
		<div class='contact-message' style='display:none'></div>
		<form action='#' style='display:none'>
		<input TYPE="text" id="customer_id" style='display:none' name="customer_id" size=10 maxlength=20><br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;<input TYPE="text" id="customer_name" name="customer_name" size=10 maxlength=20><br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;性别&nbsp;<select id="customer_sex" name="customer_sex" onChange="">
  		<option value="-">--</option>
        <option value="男">男</option>
        <option value="女">女</option>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;年龄&nbsp;<input TYPE="text" id="customer_age" name="customer_age" size=10 maxlength=10>&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;职位&nbsp;<input TYPE="text" id="customer_job" name="customer_job" size=10 maxlength=10><br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;省份&nbsp;<input TYPE="text" id="customer_province" name="customer_province" size=10 maxlength=10>&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;城市&nbsp;<input TYPE="text" id="customer_city" name="customer_city" size=10 maxlength=10>&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;邮编&nbsp;<input TYPE="text" id="customer_post" name="customer_post" size=10 maxlength=10><br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;手机&nbsp;<input TYPE="text" id="customer_mobile" name="customer_mobile" size=12 maxlength=12>&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;固话&nbsp;<input TYPE="text" id="customer_fix" name="customer_fix" size=14 maxlength=14>&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;email&nbsp;<input TYPE="text" id="customer_mail" name="customer_mail" size=15 maxlength=10><br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;类别&nbsp;<input TYPE="text" id="customer_class" name="customer_class" size=10 maxlength=10>&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;数据来源&nbsp;<input TYPE="text" id="customer_source" name="customer_source" size=10 maxlength=10><br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;备注&nbsp;<textarea id="customer_other" name="customer_other" cols="50" rows="3"></textarea><br><br>
		<label>&nbsp;</label>
		<button type='submit' class='contact-send contact-button' tabindex='1006'>提交</button>
		<button type='submit' class='contact-cancel contact-button simplemodal-close' tabindex='1007'>取消</button>
		<br/>
		</form>		
	</div>
</div>