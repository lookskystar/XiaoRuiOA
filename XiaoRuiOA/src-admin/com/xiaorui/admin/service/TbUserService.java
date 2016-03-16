package com.xiaorui.admin.service;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.util.PageModel;

public interface TbUserService {
	

	/**
	 * 用户登陆
	 */
	@SuppressWarnings("unchecked")
	public Map login(String username, String password);
	
	/**
	 * 根据角色id查询功能列表
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List> getFunctionsByUser(Integer roleid);
	
	/**
	 * 
	 * 根据id查询功能名称
	 */
	@SuppressWarnings("unchecked")
	public Map findFunnameById(Integer funID);
	
	/**
	 * 查询所有故障
	 */
	public PageModel findAllProblem(String problemName);
	
	/**
	 * 新增故障内容
	 */
	public String saveProblem(String problem_name);
	
	/**
	 * 根据故障id查询故障
	 */
	@SuppressWarnings("unchecked")
	public Map findProblemById(Integer problemId);
	
	/**
	 * 修改故障内容
	 */
	public String updateProblem(String problemName,Integer problemId);
	
	/**
	 * 删除故障内容
	 */
	public String deleteProblemById(Integer problemId);
	
	/**
	 * 查询所有地区
	 */
	@SuppressWarnings("unchecked")
	public List findArea(String areaName,Integer areaId);
	
	/**
	 * 新增地区
	 */
	public String saveArea(String areaName);
	
	/**
	 * 编辑地区
	 */
	public String updateArea(String areaName,Integer areaId);

	/**
	 * 查询所有客户信息
	 * */
	public PageModel findAllCustom(String area,String name);

	// 删除客户
	public void deleteCustom(int id);

	// 保存客户
	public void saveCustom(String name, String tel, String py, String bz);

	// 根据id查询客户
	public Map findCustomById(int id);

	// 跟新客户信息
	public void updateCustom(int id, String name, String tel, String area,String py, String bz);
	
	// 查询重名客户
	public int findCustomName(String name);
}
