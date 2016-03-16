package com.xiaorui.test.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaorui.common.util.PageModel;
import com.xiaorui.test.dao.TestDao;
import com.xiaorui.test.service.TestService;

/**
 * 测试service的实现类
 * @author dell
 *
 */
public class TestServiceImpl implements TestService{
	@Resource(name="testDao")
	private TestDao testDao;

	public void saveUser(String username, String password, String sex) {
		testDao.saveUser(username, password, sex);
	}

	public void delUserById(Long id) {
		testDao.delUserById(id);
	}

	@SuppressWarnings("unchecked")
	public Map getUserById(Long id) {
		return testDao.getUserById(id);
	}

	@SuppressWarnings("unchecked")
	public List getUserByName(String name) {
		return testDao.getUserByName(name);
	}

	public PageModel getUserBySex(String sex) {
		return testDao.getUserBySex(sex);
	}

	public void updateUserName(Long id, String name) {
		testDao.updateUserName(id, name);
	}
}
