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

	//增加班组
	function toAdd(){
	   	top.Dialog.open({URL:"<%=basePath%>usersAction!addDeptInput.do",Width:400,Height:250,Title:"添加部门"});
	}

	//修改班组
	function toEdit() {
		var dept_id=document.getElementById("userIframe").contentWindow.document.getElementById("dept_id").value;
		if(dept_id!=null && dept_id!=undefined && dept_id!=''){
			top.Dialog.open({URL:"<%=basePath%>usersAction!editDeptInput.do?dept_id="+dept_id,Width:400,Height:250,Title:"修改部门"});
		}else{
			top.Dialog.alert("请选择部门!");
		}	
	}

	//删除班组
	function toDelete(){
	    var dept_id=document.getElementById("userIframe").contentWindow.document.getElementById("dept_id").value;
		if(dept_id!=null && dept_id!=undefined && dept_id!=''){
		    window.location.href="<%=basePath%>usersAction!delDept.do?dept_id="+dept_id; 
		}else{
			top.Dialog.alert("请选择部门!");
		}	    
	}
	</script>
  </head>
  
 <body>
	 <div id="scrollContent">
	    <input type="hidden" value="${dep.dept_id}" id="deptId"/>
	 	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	 	  	<tr>
		 		<td width="22%" class="ver01">
			 		<div class="box3"  panelTitle="部门目录" panelHeight="480" overflow="auto" showStatus="false">
			 			<div>
				 		   <button  onclick="toAdd()"><span class="icon_add">新增</span></button>&nbsp;&nbsp;&nbsp;
				 		   <button  onclick="toEdit()"><span class="icon_edit">修改</span></button>&nbsp;&nbsp;&nbsp;
				 		   <button  onclick="toDelete()"><span class="icon_delete">删除</span></button>
			     		</div>
				 		<script type="text/javascript">
				 				var d = new dTree('d');
								d.add(0,-1,'全部','<%=basePath%>usersAction!listUsers.do','全部人员','userIframe');
								<c:forEach var="dep" items="${depts}">
								   d.add(${dep.dept_id},0,'${dep.dept_name}','<%=basePath%>usersAction!listUsers.do?dept_id=${dep.dept_id}','${dep.dept_name}','userIframe');
								</c:forEach>
								document.write(d);
				 		</script>
			 		</div>
		 	    </td>
		 		<td class="ver01">
		 			<iframe scrolling="no" width="100%" frameBorder=0 id="userIframe" name="userIframe" onload="iframeHeight('userIframe')" src="<%=basePath%>usersAction!listUsers.do"  allowTransparency="true"></iframe>
		 		</td>
			</tr>
	 	</table>
	</div>
</body>
</html>