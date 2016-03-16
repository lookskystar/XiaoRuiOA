<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	
	<!--引入组件start-->
	<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
	<!--引入弹窗组件end-->
	<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
	<script type="text/javascript" src="js/text/text-overflow.js"></script>
	<script type="text/javascript">

	    $(function(){
	    	if(top.Dialog!=null){
	    		top.Dialog.close();
	    	}
	    	<c:if test="${!empty message}">
				top.Dialog.alert('${message}');
		    </c:if>
	    });
	    
		function toadd(){
			top.Dialog.open({URL:"<%=basePath%>problemAction!addProblemInput.do",Width:360,Height:240,Title:"添加故障"});
		}
		
		function todelete(problemId){
			top.Dialog.confirm("确认删除吗？",function(){
			window.location.href="<%=basePath%>problemAction!deleteProblemById.do?problemId="+problemId;
			})
	    }
	
		function toedit(problemId){
			top.Dialog.open({URL:"<%=basePath%>problemAction!editProblemInput.do?problemId="+problemId,Width:360,Height:240,Title:"修改故障"});
			}
	</script>
  </head>
  
 <body>
	<form action="problemAction!problemInput.do" method="post">
	<input type="hidden" id="problem_id" value="${problem_id }"/>
	 	<div class="box3"  >
		 	<table>
		 		<tr>
		 		<td>关键字：<input type="text" watermark="输入故障关键字"  name="problemName" value="${problem_content }"/></td>
		 		<td><input type="submit" value="查询"/></td>
		 		<td><input type="button" value="新增" onclick="toadd()"/></td>
		 		</tr>
		 	</table>	
		</div>
		<div id="scrollContent">
			<table class="tableStyle">
			<tr>
				<th align="center" width="30%">故障ID</th>
				<th align="center" width="35%">故障名称</th>
				<th align="center" width="35%">操作</th>
			</tr>
			<c:if test="${!empty pm.datas}">
			   	<c:forEach items="${pm.datas}" var="problem">
					<tr align="center">
						<td>${problem.problem_id}</td>
						<td>${problem.problem_content}</td>
						<td>
						   <a href="javascript:void(0);" onclick="toedit(${problem.problem_id})" style="color:blue;">编辑</a>&nbsp;
						   <a href="javascript:void(0);" onclick="todelete(${problem.problem_id});" style="color:blue;">删除</a>&nbsp;
						</td>
					</tr>
				</c:forEach>
		 	</c:if>
			 <c:if test="${empty pm.datas}">
			     <tr> <td class="ver01" align="center" colspan="10">没有相应的故障信息!</td></tr>
			  </c:if>
		</table>
	<!-- 处理分页 -->
	<div class="float_left padding5">  共有${pm.count}条信息</div>
	<div class="float_right padding5 paging">
		<div class="float_left padding_top4">
			<pg:pager maxPageItems="${pageSize }" url="problemAction!problemInput.do" items="${pm.count }" export="page1=pageNumber">
				<pg:param name="problem_id" value="${problem_id }" />
				<pg:param name="name" value="${problem_content }" />
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
	</div>
	</div>
	<!-- 处理分页end -->
</body>
</form>
</html>