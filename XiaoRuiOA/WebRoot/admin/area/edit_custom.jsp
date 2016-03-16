<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑</title>
    <base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--框架必需start-->
	<script type="text/javascript" src="js/jquery-1.4.js"></script>
	<script type="text/javascript" src="js/framework.js"></script>
	<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
	<script src="js/form/stepForm.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
	<script type="text/javascript" src="js/localdata_friends.js"></script>
	
	
    <script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="js/attention/floatPanel.js"></script>
	<script type="text/javascript" src="js/attention/messager.js"></script>
	<!-- 解决IE6、7不兼容JSON.stringify问题 -->
	<script type="text/javascript" src="js/json2.js"></script>
	<!--框架必需end-->
	<script>
		function checkForm(){
			var name = $("#name").val();
			var tel = $("#tel").val();
			var py = $("#py").val();
			if(name == null || name == ""){
				top.Dialog.alert("姓名不能为空！");
				return false;
			}
			if(tel == null || tel == ""){
				top.Dialog.alert("电话不能为空！");
				return false;
			}
			if(py == null || py == ""){
				top.Dialog.alert("拼音不能为空！");
				return false;
			}
			return true;
		}
	</script>
<body>
<div>
<form method="post" action="areaAction!updateCustom.action" target="frmright" onsubmit="return checkForm()">
	<input type="hidden" value="${custom.mail_id }" name="id"/>
	<table class="tableStyle" transMode="true">
		<tr>
			<td>姓名: </td>
			<td>
				<input type="text" name="name" value="${custom.mail_name }" id="name"></input>
			</td>
		</tr>
		<tr>
			<td>电话: </td>
			<td>
				<input type="text" name="tel" value="${custom.mail_phone }" id="tel"></input>
			</td>
		</tr>
		<tr>
			<td>单位: </td>
			<td>
				<input type="text" name="area" value="${custom.mail_area }"></input>
			</td>
		</tr>
		<tr>
			<td>拼音: </td>
			<td>
				<input type="text" name="py" value="${custom.mail_py }" id="py"></input><font color="red">*</font>
			</td>
		</tr>
		<tr>
			<td>备注: </td>
			<td>
				<textarea name="bz" value="${custom.mail_comment }"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" value=" 提 交 "/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset"  value=" 重 置 "/>
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
