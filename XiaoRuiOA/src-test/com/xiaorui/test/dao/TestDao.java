package com.xiaorui.test.dao;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.util.PageModel;

/**
 * ����Dao��
 * @author dell
 *
 */
public interface TestDao {
	/**
	 * ����ID��ѯ�û�
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getUserById(Long id);
	
	/**
	 * ����name�õ�List����
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getUserByName(String name);
	
	/**
	 * �����Ա�õ�һ����ҳ��PageModel
	 * @param sex
	 * @return
	 */
	public PageModel getUserBySex(String sex);
	
	/**
	 * ���һ���û�
	 * @param username
	 * @param password
	 * @param sex
	 */
	public void saveUser(String username,String password,String sex);
	
	/**
	 * �����û�����
	 * @param id
	 * @param name
	 */
	public void updateUserName(Long id,String name);
	
	/**
	 * ɾ��һ���û�
	 * @param id
	 */
	public void delUserById(Long id);

}
