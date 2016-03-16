<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
	<!--框架必需start-->
	<script type="text/javascript" src="js/jquery-1.4.js"></script>
	<script type="text/javascript" src="js/framework.js"></script>
	<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
	<!--框架必需end-->
	<script type="text/javascript" src="js/tree/dtree/dtree.js"></script>
	<link href="js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
	<!--截取文字start-->
	<script type="text/javascript" src="js/text/text-overflow.js"></script>
	<!--截取文字end-->
	<!--修正IE6支持透明PNG图start-->
    <script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
    <!--修正IE6支持透明PNG图end-->
	<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
	<script>
		function checkform(){
			    
				if($("#problem_name").val()==''){
					alert("故障内容不能为空！");
					return false;
				}
			}
		</script>
  </head>
<body>
  	<form action="problemAction!addProblem.action" method="post" id="subForm" target="frmright" onsubmit="return checkform()">   
		<table transMode="true">
		   
		    <tr>
				<td>故障内容: </td>
				<td>
					<!-- <input  type="text" name="problem_name" id="problem_name"></input><font color="red">&nbsp;&nbsp;*</font>  -->
					<textarea name="problem_name" id="problem_name" maxNum="100" watermark="请输入故障内容，字数限制100以内！" style="word-wrap: break-word;word-break: break-all;"></textarea><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			
            <tr>
				<td colspan="2" >
					<input type="submit" value=" 提 交 "/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value=" 重 置 "/>
				</td>
			</tr>      
		</table>
  	</form>  
</body>
</html>