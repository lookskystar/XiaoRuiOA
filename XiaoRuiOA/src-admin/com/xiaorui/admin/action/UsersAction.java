package com.xiaorui.admin.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.xiaorui.admin.service.UsersService;
import com.xiaorui.common.util.PageModel;

public class UsersAction{
	@Resource(name="usersService")
	private UsersService usersService;
	
	private String dept_name;
	private String name;
	private Integer dept_id;
	private Integer role_id;


	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
	
	
	/**
	 * 进入用户管理页面
	 */
	public String usersInput() throws Exception {
		request.setAttribute("depts", usersService.findAllDept());//查询班组
		request.setAttribute("roles",usersService.findAllRoles());//查询角色
		return "userindex";
	}
	
	/**
	 * 查询用户
	 */
	public String listUsers() throws Exception{
		PageModel pm=usersService.findUsers(name, dept_id,role_id);
		request.setAttribute("pm", pm);
		request.setAttribute("depts",usersService.findAllDept());
		request.setAttribute("roles",usersService.findAllRoles());
		return "users";
	}
	
	/**
	 * 进入新增部门
	 */
	public String addDeptInput() throws Exception {
		
		return "adddept";
	}

	/**
	 * 新增部门
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String addDept() throws Exception{
		List list=usersService.getDeptByName(dept_name);
		if(list==null){
			usersService.saveDept(dept_name);
			request.setAttribute("message", "班组添加成功");
		}else{
			request.setAttribute("message", "班组已经存在,添加失败");
		}
		return usersInput();
	}
	
	/**
	 * 进入编辑部门
	 */
	@SuppressWarnings("unchecked")
	public String editDeptInput() throws Exception {
		Map map=usersService.getDeptById(Integer.valueOf(request.getParameter("dept_id")));
		request.setAttribute("depts", map);
		return "editdept";
	}
	
	/**
	 * 编辑部门
	 * @return
	 * @throws Exception
	 */
	public String editDept() throws Exception{
		dept_id = Integer.valueOf(request.getParameter("dept_id"));
		dept_name = request.getParameter("dept_name");
		usersService.updateDept(dept_id,dept_name);
		request.setAttribute("message", "部门修改成功");
		return usersInput();
	}
	
	/**
	 * 删除部门
	 */
	public String delDept() throws Exception {
		Integer dept_id = Integer.parseInt(request.getParameter("dept_id"));
		long count = usersService.countUser(dept_id);
		if (count == 0) {
			usersService.delDept(dept_id);
			request.setAttribute("message", "部门信息删除成功");
		} else {
			request.setAttribute("message", "该部门存在用户不能删除");
		}
		return usersInput();
	}
	
	/**
	 * 删除用户
	 * @return
	 * @throws Exception
	 */
	public String delUsers() throws Exception {
		String result="failure";
		String userIdArray[] = ServletActionContext.getRequest().getParameter("users").split(",");
		if (userIdArray.length > 0) {
			usersService.delTbUsers(userIdArray);
			result = "success";
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}
	
	/**
	 * 进入查看用户详情
	 */
	@SuppressWarnings("unchecked")
	public String userInfoListInput() throws Exception {
		Map users=usersService.getTbUsersById(Integer.valueOf(request.getParameter("user_id")));
		request.setAttribute("depts", usersService.findAllDept());
		request.setAttribute("roles",usersService.findAllRoles());
		request.setAttribute("user", users);
		return "userinfo";
	}
	
	/**
	 * 进入新增用户
	 */
	public String addUserInput() throws Exception {
		request.setAttribute("deptList", usersService.findAllDept());
		request.setAttribute("roleList",usersService.findAllRoles());
		return "adduser";
	}
	
	/**
	 * 新增用户
	 * @return
	 * @throws Exception
	 */
	public String addUser() throws Exception{
		Integer user_role_id = Integer.parseInt(request.getParameter("user_role_id"));
		Integer user_dept_id = Integer.parseInt(request.getParameter("user_dept_id"));
		String user_xm = request.getParameter("user_xm");
		String user_name = request.getParameter("user_name");
		String user_phone = request.getParameter("user_phone");
		String user_pwd = request.getParameter("user_pwd");
		try{
			usersService.saveUsers(user_role_id,user_dept_id,user_xm,user_name,user_phone,user_pwd);
			request.setAttribute("message", "用户信息添加成功");
		}catch (Exception e) {
			request.setAttribute("message", "用户信息添加失败");
		}
		return usersInput();
	}
	
	
	/**
	 * 进入编辑用户
	 */
	@SuppressWarnings("unchecked")
	public String editUserInput() throws Exception {
		Map user=usersService.getTbUsersById(Integer.valueOf(request.getParameter("user_id")));
		request.setAttribute("depts", usersService.findAllDept());
		request.setAttribute("roles",usersService.findAllRoles());
		request.setAttribute("user", user);
		return "edituser";
	}
	
	/**
	 * 编辑用户
	 * @throws Exception
	 */
	public String editTbUser() throws Exception{
		Integer user_id = Integer.valueOf(request.getParameter("user_id"));
		Integer role_id = Integer.valueOf(request.getParameter("role_id"));
		Integer depe_id = Integer.valueOf(request.getParameter("dept_id"));
		String user_xm = request.getParameter("user_xm");
		String user_phone = request.getParameter("user_phone");
		String user_name = request.getParameter("user_name");
		String user_pwd = request.getParameter("user_pwd");
		usersService.updateTbUser(user_id,role_id,depe_id,user_xm,user_phone,user_name,user_pwd);
		request.setAttribute("message", "用户信息编辑成功");
		return usersInput();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String deptName) {
		dept_name = deptName;
	}
	
	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer deptId) {
		dept_id = deptId;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}
}
