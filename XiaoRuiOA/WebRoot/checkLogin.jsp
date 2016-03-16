<%
if(request.getSession().getAttribute("session_user")==null){
	response.sendRedirect("login.jsp");
}
%>
