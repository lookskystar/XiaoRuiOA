<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--框架必需start-->
	<script type="text/javascript" src="js/jquery-1.4.js"></script>
	<script type="text/javascript" src="js/framework.js"></script>
	<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
	<script src="js/form/stepForm.js" type="text/javascript"></script>
	
	<link type="text/css" href="css/jquery.autocomplete.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="js/attention/floatPanel.js"></script>
	<script type="text/javascript" src="js/attention/messager.js"></script>
	<!-- 解决IE6、7不兼容JSON.stringify问题 -->
	<script type="text/javascript" src="js/json2.js"></script>
	<!--框架必需end-->
	<script type="text/javascript">
		$(function(){
		   	<c:if test="${!empty message}">
		   		top.Dialog.close();
				top.Dialog.alert('${message}');
		    </c:if>
		   });
		
		//添加
		function add(){
			top.Dialog.open({URL:"areaAction!addCustomInput.action",Title:"添加",Width:480,Height:360});
		}
	
		//编辑
		function edit(id){
			top.Dialog.open({URL:"areaAction!editCustomInput.action?id="+id,Title:"编辑",Width:480,Height:360});
		}
	
		//删除
		function del(id){
			window.location.href="areaAction!delCustom.action?id="+id;
		}
	
	</script>
</head>
<body>
<div id="scrollContent">
	<form action="areaAction!customInput.action" method="post">
		<div class="box3" panelTitle="通讯录" roller="false" showStatus="false">
			<table>
		    	<tr>
		          <td>单位：<input type="text" value="${area }" name="area" />
				  </td>
				  <td>姓名：<input type="text" value="${name }" name="name" /></td>
			      <td ><button type="submit">查询</button></td>
			      <td ><button onclick="add();">添加</button></td>
		       </tr>
			</table>
		</div>
	</form>
	<div>
	    <table class="tableStyle"  useMultColor="true" >
	        <tr>
	            <th width="5%">序号</th>
				<th width="15%" align="center">姓名</th>
	            <th width="15%" align="center">电话</th>
	            <th width="15%" align="center">单位</th>
	            <th width="15%" align="center">拼音</th>
				<th width="15%" align="center">备注</th>
	            <th width="20%" align="center">操作</th>
	        </tr>
	        <c:forEach var="date" items="${date.datas}">
	        <tr align="center">
	            <td>${date.mail_id }</td>
				<td>${date.mail_name }</td>
				<td>${date.mail_phone }</td>
				<td>${date.mail_area }</td>
				<td>${date.mail_py }</td>
				<td>${date.mail_comment }</td>
	            <td >
	                <a href="javascript:void(0);" onclick="edit(${date.mail_id });" style="color:blue;">编辑</a>&nbsp;&nbsp;
					<a href="javascript:void(0);" onclick="del(${date.mail_id });" style="color:blue;">删除</a>
	            </td>
	        </tr>
	        </c:forEach>
	    </table>
	</div>
	<!-- 处理分页 -->
	<div class="float_left padding5">共有信息${date.count}条,每页显示10条</div>
	<div class="float_right padding5 paging">
		<div class="float_left padding_top4">
			<pg:pager maxPageItems="${pageSize }" url="areaAction!customInput.action" items="${date.count }" export="page1=pageNumber">
				<pg:param name="psize" value="10" />
				<pg:param name="area" value="${area }" />
				<pg:param name="name" value="${name }" />
		  		<pg:first>
					 <span><a href="${pageUrl }" id="first">首页</a></span>
		 		</pg:first>
		 		<pg:prev>
					  <span><a href="${pageUrl }">上一页</a></span>
		  		</pg:prev>
	  	  		<pg:pages>
					<c:choose>
						<c:when test="${page1==pageNumber }">
							<span class="paging_current"><a href="javascript:;">${pageNumber }</a></span>
						</c:when>
						<c:otherwise>
							<span><a href="${pageUrl }">${pageNumber }</a></span>
						</c:otherwise>
					</c:choose>
		  		</pg:pages>
		  		<pg:next>
				    <span><a href="${pageUrl }">下一页</a></span>
		  		</pg:next>
		  		<pg:last>
				  	<span><a href="${pageUrl }">末页</a></span>
		 		</pg:last>
		 	</pg:pager>
			<div class="clear"></div>
		</div>
	<!-- 处理分页end -->
	</div>
</div>
</body>
</html>
