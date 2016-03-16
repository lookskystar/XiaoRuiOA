package com.xiaorui.admin.dao;

import java.util.List;
import java.util.Map;


public interface RolesDao {

	/**
	 * 保存角色
	 */
	public void saveRole(String roleName,String rolePy,Integer roleLevel);

	/**
	 * 根据角色ID 删除角色
	 */
	public void deleteRole(Integer roleID);

	/**
	 * 查找角色下是否存在用户
	 */
	public long countUsers(Integer roleID);

	/**
	 * 根据角色ID查找角色
	 */
	@SuppressWarnings("unchecked")
	public Map getTbRolesById(Integer roleID);
	
	/**
	 * 根据角色名字查找角色
	 */
	@SuppressWarnings("unchecked")
	public List getTbRolesByName(String roleName);

	/**
	 * 修改角色
	 */
	public void updateRole(Integer roleId,String roleName,String rolePy,Integer roleLevel);
	
	/**
	 * 查询所有角色
	 * */
	@SuppressWarnings("unchecked")
	public List listRoles();
	
//	/**
//	 * 列出主功能列表
//	 * */
//	@SuppressWarnings("unchecked")
//	public List listMainFunctionPrivs();
	
	/**
	 * 列出次功能列表
	 * */
	@SuppressWarnings("unchecked")
	public List listSecFunctionPrivs();
	
	/**
	 * 通过功能ID查询功能名
	 * */
	@SuppressWarnings("unchecked")
	public Map findFunnameById(Integer parentid);
	
	/**
	 * 保存角色功能表,将角色与功能关联
	 */
	public void saveRoleFunPrivs(Integer roleID,Integer s);
	
	/**
	 * 根据角色ID删除角色功能表
	 */
	public void deleteRoleFunPrivs(Integer roleID);
	
	/**
	 * 根据角色ID列出角色功能表
	 * */
	@SuppressWarnings("unchecked")
	public List getRolesFunctionById(Integer roleID);
}
