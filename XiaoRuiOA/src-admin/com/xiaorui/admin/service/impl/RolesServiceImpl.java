package com.xiaorui.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaorui.admin.dao.RolesDao;
import com.xiaorui.admin.service.RolesService;

public class RolesServiceImpl implements RolesService {

	@Resource(name = "rolesDao")
	private RolesDao rolesDao;

	public long countUsers(Integer roleID) {
		long count=rolesDao.countUsers(roleID);
		return count;
	}

	public String deleteRole(Integer roleID) {
		rolesDao.deleteRole(roleID);
		rolesDao.deleteRoleFunPrivs(roleID);
		return "success";
		
	}

	@SuppressWarnings("unchecked")
	public Map getTbRolesById(Integer roleID) {
		return rolesDao.getTbRolesById(roleID);
	}

	@SuppressWarnings("unchecked")
	public List getTbRolesByName(String roleName) {
		return rolesDao.getTbRolesByName(roleName);
	}

	@SuppressWarnings("unchecked")
	public List listRoles() {
		return rolesDao.listRoles();
	}

	public void saveRole(String roleName,String rolePy,Integer roleLevel) {
		rolesDao.saveRole(roleName,rolePy,roleLevel);
		
	}

	public void updateRole(Integer roleId,String roleName,String rolePy,Integer roleLevel) {
		rolesDao.updateRole(roleId,roleName,rolePy,roleLevel);
		
	}

//	public List<DictFunctions> listMainFunctionPrivs() {
//		return rolesDao.listMainFunctionPrivs();
//	}

	@SuppressWarnings("unchecked")
	public List listSecFunctionPrivs() {
		return rolesDao.listSecFunctionPrivs();
	}

	@SuppressWarnings("unchecked")
	public Map findFunnameById(Integer parentid) {
		return rolesDao.findFunnameById(parentid);
	}

	@SuppressWarnings("unchecked")
	public void saveRolesFunction(String[] ids,Integer roleID) {
        rolesDao.deleteRoleFunPrivs(roleID);
        for(String s:ids){
        	rolesDao.saveRoleFunPrivs(roleID,Integer.parseInt(s));
        }
	}

	@SuppressWarnings("unchecked")
	public List getRolesFunctionById(Integer roleID) {
		return rolesDao.getRolesFunctionById(roleID);
	}

}
