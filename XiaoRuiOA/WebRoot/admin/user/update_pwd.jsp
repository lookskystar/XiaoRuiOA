<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左侧菜单</title>
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/framework.js"></script>
<script type="text/javascript" src="js/jquery.anchor.1.0.js"></script>
<script type="text/javascript" src="js/table/treeTable.js"></script>
<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
<!--框架必需end-->

<!--截取文字start-->
<script type="text/javascript" src="js/text/text-overflow.js"></script>
<!--截取文字end-->

<!--修正IE6支持透明PNG图start-->
<script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
<!--修正IE6支持透明PNG图end-->
<script type="text/javascript">
  function validate(){
	   var pwd=$("#pwd").val();
	   var old_pwd=$("#old_pwd").val();
	   var new_pwd=$("#new_pwd").val();
	   var new_pwd_again=$("#new_pwd_again").val();
	   if(old_pwd==""){
		   top.Dialog.alert("原始密码不能为空!");
		   return false;
	   }
	   if(old_pwd!=pwd){
		   top.Dialog.alert("原始密码不正确!");
		   return false;
	   }
	   if(new_pwd==""){
		   top.Dialog.alert("新密码不能为空!");
		   return false;
	   }
	   if(new_pwd_again!=new_pwd){
		   top.Dialog.alert("密码确认不一致!");
		   return false;
	   }
	   return true;
  }

  function submit(){
	  var new_pwd=$("#new_pwd").val();
	  if(validate()){
		  $.post("<%=basePath%>loginAction!updatePwd.do",{"new_pwd":new_pwd},function(data){
			   if(data=="session_null"){
				   top.Dialog.alert("用户失效，请重新登录!",function(){
					   top.location="<%=basePath%>loginAction!loginOut.do";
				   });
			   }else if(data=="success"){
				   top.Dialog.alert("密码修改成功!",function(){
					   top.location="<%=basePath%>loginAction!loginOut.do";
				   });
			   }else{
				   top.Dialog.alert("密码修改失败!");
			   }
		  });
	  }
  }
</script>
<style type="text/css">
*html{
 background-image:url(about:blank);
 background-attachment:fixed;
 }
 
#sign{
 _position:absolute;
 _bottom:auto;
 _top:expression(eval(document.documentElement.scrollTop));
 _margin-top:5px;
 _margin-left:5px;
 }
 
#allsign{
_position:absolute;
_bottom:auto;
_top:expression(eval(document.documentElement.scrollTop));
_margin-top:5px;
_margin-left:75px;
}

#newreport{
_position:absolute;
_bottom:auto;
_top:expression(eval(document.documentElement.scrollTop));
_margin-top:5px;
_margin-left:145px;
}
</style>
</head>
<body>
<table class="tableStyle" style="text-align: center;margin-top: 30px;" id="checkItemTab">
    <input type="hidden" value="${sessionScope.session_user.user_pwd }" id="pwd"/>
	<tr style="background-color: lightblue;"><td colspan="11" align="left" style="font-weight: bolder;">用户密码修改</td></tr>
	<tr>
	  <td align="right" width="40%">用户姓名:</td>
	  <td align="left"><input type="text" style="width:180px;" value="${sessionScope.session_user.user_xm }" readonly="readonly"/></td>
	</tr>
	<tr>
	  <td align="right" width="40%">请输入原始密码:</td>
	  <td align="left"><input type="password" style="width:180px;" id="old_pwd"/><font color="red">&nbsp;&nbsp;*不能为空!</font></td>
	</tr>
	<tr>
	  <td align="right" width="40%">请输入新密码:</td>
	  <td align="left"><input type="password" style="width:180px;" id="new_pwd"/><font color="red">&nbsp;&nbsp;*不能为空!</font></td>
	</tr>
	<tr>
	  <td align="right" width="40%">请确认新密码:</td>
	  <td align="left"><input type="password" style="width:180px;" id="new_pwd_again"/><font color="red">&nbsp;&nbsp;*不能为空!</font></td>
	</tr>
	<tr>
	  <td align="center" colspan="2"><button style="width: 120px;" onclick="submit();">确&nbsp;&nbsp;&nbsp;认</button></td>
	</tr>
</table>
</html>