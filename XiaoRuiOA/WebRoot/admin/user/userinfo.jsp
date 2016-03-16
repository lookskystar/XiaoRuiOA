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
	    //关闭此页面
		function toclose(){
			top.Dialog.close();
		}
    </script>
  </head>
<body>
    <form action="usersAction!userInfoList.do" method="post" target="frmright"> 
  	    <input type="hidden" value="${user.user_id }" name="userId"/>
		<table class="tableStyle" formMode="true">
		<tr>
				<td>职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位: </td>
				<td>
					  <c:forEach items="${roles}" var="entry">
    	                  <c:if test="${user.user_role_id==entry.role_id}">${entry.role_name}</c:if>
    	              </c:forEach>
				</td>
				<td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名: </td>
				<td>
					${user.user_xm }
				</td>
			</tr>
	 		<tr>
				<td>部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门: </td>
				<td>
					<c:forEach items="${depts}" var="entry">
    	               <c:if test="${user.user_dept_id==entry.dept_id}">${entry.dept_name}</c:if>
    	            </c:forEach>
				</td>
				<td>电话号码: </td>
				<td>
					${user.user_phone }
				</td>
			</tr>
			<tr>	
				<td>登陆名称: </td>
				<td>
					${user.user_name }
				</td>
				<td>登录密码: </td>
				<td>
					${user.user_pwd }
				</td>
			</tr>
			<tr>
			    <td colspan="4" align="center"><input type="button" value="确定" onclick="toclose();"/></td>
			</tr>
			
		</table>
	</form>
</body>
</html>