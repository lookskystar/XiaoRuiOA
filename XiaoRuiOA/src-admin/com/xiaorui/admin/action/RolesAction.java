package com.xiaorui.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.xiaorui.admin.service.RolesService;

public class RolesAction {

	@Resource(name = "rolesService")
	private RolesService roleService;

	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();

	/**
	 * 进入角色管理
	 */
	@SuppressWarnings("unchecked")
	public String rolesInput() throws Exception {
		List roleList = roleService.listRoles();
		request.setAttribute("roleList", roleList);
		return "roles";
	}

	/**
	 * 进入添加角色
	 */
	public String addRoleInput() throws Exception{
		return "addrole";
	}
	
	/**
	 * 添加角色
	 */
	@SuppressWarnings("unchecked")
	public String addRole() throws Exception {
		String roleName= request.getParameter("roleName");
		String rolePy= request.getParameter("rolePy");
		Integer roleLevel= Integer.valueOf(request.getParameter("roleLevel"));
		List roles=roleService.getTbRolesByName(roleName);
		if(roles==null){
			roleService.saveRole(roleName,rolePy,roleLevel);
			request.setAttribute("message", "角色信息添加成功!");
		}else{
			request.setAttribute("message", "角色已经存在,添加失败!");
		}
		
		return rolesInput();
	}

	/**
	 * 删除角色
	 */
	public String deleteRoleById() throws Exception {
		Integer roleID = Integer.parseInt(request.getParameter("roleId"));
		long count = roleService.countUsers(roleID);
		if (count == 0) {
			roleService.deleteRole(roleID);
			request.setAttribute("message", "角色信息删除成功!");
		} else {
			request.setAttribute("message", "该角色存在用户,不能删除!");
		}
		return rolesInput();
	}

	/**
	 * 进入编辑角色
	 */
	@SuppressWarnings("unchecked")
	public String editRoleInput() {
		Integer roleID = Integer.parseInt(request.getParameter("roleId"));
		Map role = roleService.getTbRolesById(roleID);
		request.setAttribute("role", role);
		return "editrole";
	}

	/**
	 * 编辑角色
	 */
	public String editRole() throws Exception {
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		String roleName = request.getParameter("roleName");
		String rolePy = request.getParameter("rolePy");
		Integer roleLevel = Integer.parseInt(request.getParameter("roleLevel"));
		roleService.updateRole(roleId,roleName,rolePy,roleLevel);
		request.setAttribute("message", "角色信息修改成功!");
		return rolesInput();
	}
	/**
	 * 进入授权页面
	 */
	@SuppressWarnings("unchecked")
	public String toPowerInput() throws Exception {
		Integer roleID = Integer.parseInt(request.getParameter("roleId"));
		Map role = roleService.getTbRolesById(roleID);
		//列出功能
		List<Map<String,List>>  functionprivs=roleService.listSecFunctionPrivs();
		Map<String,List> funMap = new LinkedHashMap<String, List>();
		Integer parentid;
		Map map =null;
		String funname=null;
		for(Map fun:functionprivs){
			parentid = (Integer) fun.get("func_parentid");
				map=roleService.findFunnameById(parentid);
				funname = (String)map.get("func_name");
			if(funMap.get(funname)==null){
				funMap.put(funname, new ArrayList());
			}
			funMap.get(funname).add(fun);
		}
		request.setAttribute("funMap", funMap);
		request.setAttribute("role", role);
		return "topower";
	}
	
	/**
	 * 根据角色Id获得该角色所拥有的功能
	 */
	@SuppressWarnings("unchecked")
	public String getRoleFunPrivsById() throws Exception {
		Integer roleID = Integer.parseInt(request.getParameter("roleId"));
		List<Map<String,List>> rolefun=roleService.getRolesFunctionById(roleID);
		JSONObject obj=new JSONObject();
		JSONArray array=new JSONArray();
		JSONObject obj2=null;
		for(Map list:rolefun){
			obj2=new JSONObject();
			obj2.put("funId", list.get("func_id"));
			array.put(obj2);
		}
		obj.put("funIds", array);
		response.setContentType("text/plain");
		response.getWriter().print(obj.toString());
		return null;
	}
	
	/**
	 * 将角色和功能关联
	 * @throws IOException 
	 */
	public String updateRoleFunPrivs() throws IOException{
		Integer roleID = Integer.parseInt(request.getParameter("roleId"));
		String ids=request.getParameter("ids");
		if(ids!=null){
	    	String[] str=ids.split("-");
	    	roleService.saveRolesFunction(str, roleID);
	    }else{
	    	roleService.saveRolesFunction(new String[]{},roleID);
	    }
	    response.setContentType("text/plain");
		response.getWriter().print("success");
		return null;
	}

}
