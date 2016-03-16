package com.xiaorui.admin.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaorui.admin.dao.UsersDao;
import com.xiaorui.admin.service.UsersService;
import com.xiaorui.common.util.PageModel;

public class UsersServiceImpl implements UsersService {
	
	@Resource(name="usersDao")
	private UsersDao usersDao;

	@SuppressWarnings("unchecked")
	public List findAllDept() {
		return usersDao.findAllDept();
	}
	
	@SuppressWarnings("unchecked")
	public List findAllRoles(){
		return usersDao.findAllRoles();
	}

	public long countUser(Integer dept_id) {
		long count=usersDao.countUser(dept_id);
		return count;
	}

	public String delDept(Integer dept_id) {
		usersDao.delDept(dept_id);
		return "success";
		
	}

	public String delTbUsers(String[] userIdArray) {
		for (int i = 0; i < userIdArray.length; i++) {
			usersDao.delTbUsers(Integer.parseInt(userIdArray[i]));
		}
		return "success";
	}

	public PageModel findUsers(String name,
			Integer dept_id,Integer user_role_id) {
		return usersDao.findUsers(name, dept_id,user_role_id);
	}

	@SuppressWarnings("unchecked")
	public Map getDeptById(Integer dept_id) {
		return usersDao.getDeptById(dept_id);
	}

	@SuppressWarnings("unchecked")
	public List getDeptByName(String bmName) {
		return usersDao.getDeptByName(bmName);
	}

	@SuppressWarnings("unchecked")
	public Map getTbUsersById(Integer userId) {
		return usersDao.getTbUsersById(userId);
	}

	public void saveDept(String dept_name) {
	    usersDao.saveDept(dept_name);
		
	}

	public void saveUsers(Integer user_role_id,Integer user_dept_id,
			String user_xm,String user_name,String user_phone,String user_pwd) {
		usersDao.saveUsers(user_role_id,user_dept_id,user_xm,user_name,user_phone,user_pwd);
		
	}

	public void updateDept(Integer dept_id,String dept_name) {
		usersDao.updateDept(dept_id,dept_name);
		
	}

	public void updateTbUser(Integer user_id,Integer role_id,Integer depe_id,
			String user_xm,String user_phone,String user_name,String user_pwd) {
		usersDao.updateTbUser(user_id,role_id,depe_id,user_xm,user_phone,user_name,user_pwd);
		
	}


}
