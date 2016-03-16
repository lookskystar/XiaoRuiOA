<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>机务检修信息管理系统</title>
    <base href="<%=basePath%>">
    
    <title>出错了</title>
	<style type="text/css">
    	a {
			color: #1b6ad8; text-decoration: none
		}
		a:hover {
			color: red
		}
    </style>
  </head>
  
  <body>
    <p>&nbsp;</p>
    <div align="center"><img  src="<%=basePath%>common/error.gif" width=329 alt="网上订餐"/></div>
    <p align="center"><font color=#ff0000>对不起,系统异常,请稍候再试！</font></p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p align="center" style="font-size: 12px;">
    	<a href="<%=basePath%>" target="_blank">返回首页</a>　 　|　　 
    	<a href="javascript:history.go(-1);">返回出错页</a>
    </p>
  </body>
</html>
