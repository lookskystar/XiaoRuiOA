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
		top.Dialog.open({URL:"<%=basePath%>areaAction!addAreaInput.do",Width:360,Height:240,Title:"添加地区"});
	}
	function toedit(areaId){
		top.Dialog.open({URL:"<%=basePath%>areaAction!editAreaInput.do?areaId="+areaId,Width:360,Height:240,Title:"修改地区"});
		}
</script>
</head>

<body>
<div class="box3"  panelTitle="地区管理" panelHeight="480" overflow="auto" showStatus="false">
	<div>
		<button onclick="toadd()"><span class="icon_add">新增</span></button>&nbsp;&nbsp;
	</div>
	<table class="tableStyle">
		<tr>
			<th align="center" width="30%">地区ID</th>
			<th align="center" width="30%">地区名称</th>
			<th align="center" width="40%">操作</th>
		</tr>
		<c:forEach var="area" items="${areas}">
				<tr align="center">
					<td>${area.area_id }</td>
					<td>${area.area_name}</td>
					<td>
					   <a href="javascript:void(0);" onclick="toedit(${area.area_id})" style="color:blue;">编辑</a>&nbsp;
					</td>
				</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>