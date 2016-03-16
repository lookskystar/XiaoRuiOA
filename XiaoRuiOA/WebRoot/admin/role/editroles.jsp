<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<!--框架必需start-->
		<script type="text/javascript" src="js/jquery-1.4.js"></script>
		<script type="text/javascript" src="js/framework.js"></script>
		<link href="css/import_basic.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" id="skin"
			prePath="<%=basePath%>" />
		<!--框架必需end-->
		<script type="text/javascript" src="js/tree/dtree/dtree.js"></script>
		<link href="js/tree/dtree/dtree.css" rel="stylesheet" type="text/css" />
		<!--截取文字start-->
		<script type="text/javascript" src="js/text/text-overflow.js"></script>
		<!--截取文字end-->
		<!--修正IE6支持透明PNG图start-->
		<script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
		<!--修正IE6支持透明PNG图end-->
		<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
		<script>
		function checkform(){
				if($("#roleName").val()==''){
					alert("职位名称不能为空！");
					return false;
				}
				if($("#py").val()==''){
					alert("职位拼音不能为空！");
					return false;
				}
			}
		</script>
	</head>
	<body>
		<form action="rolesAction!editRole.do" method="post"
			target="frmright" onsubmit="return checkform()">
			<input type="hidden" name="roleId" value="${role.role_id}"></input>
			<table class="tableStyle" transMode="true">
				<tr>
					<td>
						职位名称:
					</td>
					<td>
						<input  type="text" name="roleName" id="roleName"
							value="${role.role_name}"></input><font color="red">&nbsp;&nbsp;*</font>
					</td>
				</tr>
				<tr>
					<td>
						职位拼音:
					</td>
					<td>
						<input  type="text" name="rolePy" id="py"  value="${role.role_py}"></input>
					</td>
				</tr>
				<tr>
				   <td>职位等级:</td>
				   <td>
					<c:if test="${role.role_level==1}">
						<input type="radio" name="roleLevel" value="0" />部门
						<input type="radio" name="roleLevel" value="1" checked="checked"/>公司
					</c:if>					
					<c:if test="${role.role_level==0}">
						<input type="radio" name="roleLevel" value="0" checked="checked"/>部门
						<input type="radio" name="roleLevel" value="1" />公司
					</c:if>
					<font color="red">&nbsp;&nbsp;*</font>
				   </td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value=" 修改 " />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value=" 重 置 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>