<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/framework.js"></script>
<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
<link  rel="stylesheet" type="text/css" id="skin"/>
<!--框架必需end-->

<!--截取文字start-->
<script type="text/javascript" src="js/text/text-overflow.js"></script>
<!--截取文字end-->
<script>
    $(function(){
    	if(top.Dialog!=null){
    		top.Dialog.close();
    	}
    	<c:if test="${!empty message}">
			top.Dialog.alert('${message}');
	    </c:if>
    });
    
	function toadd(){
		top.Dialog.open({URL:"<%=basePath%>rolesAction!addRoleInput.do",Width:360,Height:240,Title:"添加职位"});
	}
	
	function todelete(roleId){
		top.Dialog.confirm("确认删除吗？",function(){
		window.location.href="<%=basePath%>rolesAction!deleteRoleById.do?roleId="+roleId;
		})
    }

	function toedit(roleId){
		top.Dialog.open({URL:"<%=basePath%>rolesAction!editRoleInput.do?roleId="+roleId,Width:360,Height:240,Title:"修改职位"});
		}
	function topower(roleId){
		top.Dialog.open({URL:"<%=basePath%>rolesAction!toPowerInput.do?roleId="+roleId,Width:360,Height:450,Title:"给职位授权"});
		}
</script>
</head>

<body>
<div class="box3"  panelTitle="职位管理" panelHeight="480" overflow="auto" showStatus="false">
	<div>
		<button onclick="toadd()"><span class="icon_add">新增</span></button>&nbsp;&nbsp;
	</div>
	<table class="tableStyle">
		<tr>
			<th align="center" width="8%">职位ID</th>
			<th align="center" width="15%">职位名称</th>
			<th align="center" width="15%">职位拼音</th>
			<th align="center" width="15%">职位等级</th>
			<th align="center" width="22%">操作</th>
		</tr>
		<c:forEach var="role" items="${roleList}">
			<!--<c:if test="${!fn:containsIgnoreCase(role.rolePy,'XTGL')}">-->
				<tr align="center">
					<td>${role.role_id}</td>
					<td>${role.role_name}</td>
					<td>${role.role_py}</td>
					<td>
					   <c:if test="${role.role_level==0}">部门</c:if>
				       <c:if test="${role.role_level==1}">公司领导</c:if>
				    </td>
					<td>
					   <a href="javascript:void(0);" onclick="toedit(${role.role_id})" style="color:blue;">编辑</a>&nbsp;
					   <a href="javascript:void(0);" onclick="todelete(${role.role_id});" style="color:blue;">删除</a>&nbsp;
					   <a href="javascript:void(0);" onclick="topower(${role.role_id});" style="color:blue;">给职位授权</a>
					</td>
				</tr>
			<!--</c:if>-->
		</c:forEach>
	</table>
</div>
</body>
</html>