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
			if($("#user_xm").val()==''){
				alert("姓名不能为空！");
				return false;
			}
			 if($("#dept_id").val()==''){
			    alert("部门不能为空！");
			    return false;
		    }
			if($("#role_id").val()==''){
			    alert("职位不能为空！");
			    return false;
		    }
		    	if($("#user_phone").val()==''){
				alert("电话不能为空！");
				return false;
			}
			if($("#user_name").val()==''){
				alert("登陆帐号不能为空！");
				return false;
			}
			if($("#user_pwd").val()==''){
				alert("登录密码不能为空！");
				return false;
			}
		
		}
		</script>
  </head>
<body>
  	<form action="usersAction!editTbUser.do" method="post" target="frmright" onsubmit="return checkform()">   
  	    <input type="hidden" value="${user.user_id }" name="user_id"/>
		<table class="tableStyle" transMode="true">
			<tr>
				<td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名: </td>
				<td>
					<input  type="text" name="user_xm" id="user_xm" value="${user.user_xm }"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
	 		<tr>
				<td>部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门: </td>
				<td>
					<select id="dept_id"  name="dept_id" class="default">
					     <option value="">请选择部门</option>
					     <c:forEach items="${depts}" var="entry">
    	                     <option value="${entry.dept_id}" <c:if test="${user.user_dept_id==entry.dept_id}">selected="selected"</c:if>>${entry.dept_name}</option>
    	                 </c:forEach>
					</select><font color="red">&nbsp;&nbsp;*</font>
				</td>
				
			</tr>
			<tr>
				<td>职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位: </td>
				<td>
					<select id="role_id" name="role_id" class="default">
					     <option value="">请选择职位</option>
					     <c:forEach items="${roles}" var="entry">
    	                      <option value="${entry.role_id}" <c:if test="${user.user_role_id==entry.role_id}">selected="selected"</c:if>>${entry.role_name}</option>
    	                 </c:forEach>
					</select><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			<tr>
				<td>电话号码</td>
				<td>
					<input type="text" name="user_phone" id="user_phone" value="${user.user_phone }"/>
				</td>
			</tr>
			<tr>	
				<td>登陆名称: </td>
				<td>
					<input  type="text" name="user_name" id="user_name" value="${user.user_name }"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			<tr>	
				<td>登录密码: </td>
				<td>
					<input  type="text" name="user_pwd" id="user_pwd" value="${user.user_pwd }"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			<tr><td></td></tr>
			<tr>
				<td colspan="4">
					<input type="submit" value=" 提 交 "/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value=" 重 置 "/>
				</td>
			</tr>
		</table>
  	</form>  
</body>
</html>