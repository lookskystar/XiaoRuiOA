package com.xiaorui.admin.dao;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.util.PageModel;

public interface UsersDao {
	
	/**
	 * 查询所有部门
	 */
	@SuppressWarnings("unchecked")
	public List findAllDept();
	
	/**
	 * 查询所有职位
	 */
	@SuppressWarnings("unchecked")
	public List findAllRoles();
	
	/**
	 * 根据条件查询人员信息，分页
	 * @return
	 */
	public PageModel findUsers(String name,Integer dept_id,Integer user_role_id);

	/**
	 * 新增部门
	 */
	public void saveDept(String dept_name);
	
	/**
	 * 通过ID查询部门信息
	 * @param DictTeams id
	 */
	@SuppressWarnings("unchecked")
	public Map getDeptById(Integer dept_id);
	
	/**
	 * 修改部门
	 */
	public void updateDept(Integer dept_id,String dept_name);
	
	/**
	 * 删除部门
	 */
	public void delDept(Integer dept_id);
	
	/**
	 * 查询班组下是否有用户
	 */
	public long countUser(Integer dept_id);
	
	/**
	 * 删除用户 
	 */
	public void delTbUsers(Integer userId);
	
	
	
	/**
	 * 根据班组查询班组对象
	 * */
	@SuppressWarnings("unchecked")
	public List getDeptByName(String bmName); 
	
	/**
	 * 根据用户ID查询用户信息
	 */
	@SuppressWarnings("unchecked")
	public Map getTbUsersById(Integer userId);
	
	
	/**
	 * 新增用户
	 */
	public void saveUsers(Integer user_role_id,Integer user_dept_id,
			String user_xm,String user_name,String user_phone,String user_pwd);
	
	
	/**
	 * 修改用户
	 */
	public void updateTbUser(Integer user_id,Integer role_id,Integer depe_id,
			String user_xm,String user_phone,String user_name,String user_pwd);
	
}
