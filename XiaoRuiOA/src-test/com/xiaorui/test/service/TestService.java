package com.xiaorui.test.service;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.util.PageModel;

/**
 * 测试Service类
 * 得到相应的信息，请以get或者find命名方法头
 * 更新相应的信息，请以update命名方法头
 * 添加相应的信息,请以save或者saveOrUpdate命名方法头
 * 删除相应的信息，请以del命名方法头
 * @author dell
 *
 */
public interface TestService {
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getUserById(Long id);
	
	/**
	 * 根据name得到List集合
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getUserByName(String name);
	
	/**
	 * 根据性别得到一个分页的PageModel
	 * @param sex
	 * @return
	 */
	public PageModel getUserBySex(String sex);
	
	/**
	 * 添加一条用户
	 * @param username
	 * @param password
	 * @param sex
	 */
	public void saveUser(String username,String password,String sex);
	
	/**
	 * 更新用户名称
	 * @param id
	 * @param name
	 */
	public void updateUserName(Long id,String name);
	
	/**
	 * 删除一个用户
	 * @param id
	 */
	public void delUserById(Long id);
	
	

}
