package com.xiaorui.admin.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaorui.admin.dao.TbUserDao;
import com.xiaorui.admin.service.TbUserService;
import com.xiaorui.common.util.PageModel;

public class TbUserServiceImpl implements TbUserService{
	@Resource(name="tbUserDao")
	private TbUserDao tbUserDao;

	@SuppressWarnings("unchecked")
	public Map login(String username, String password) {
		return tbUserDao.login(username, password);
	}

	@SuppressWarnings("unchecked")
	public Map<String, List> getFunctionsByUser(Integer roleid) {
		//列出功能
		Map<String, List> funMap = new LinkedHashMap<String, List>();
			
		List<Map<String,List>> functionprivs=tbUserDao.getFunPrivsByRole(roleid);
		Map map=null;
		String funname = null;
		Integer parentid = null;
		for(Map fun:functionprivs){
			parentid = (Integer) fun.get("func_parentid");
				map=tbUserDao.findFunnameById(parentid);
				funname = (String)map.get("func_name");
			if(funMap.get(funname)==null){
				funMap.put(funname, new ArrayList());
			}
			funMap.get(funname).add(fun);
		}
		return funMap;
	}

	@SuppressWarnings("unchecked")
	public Map findFunnameById(Integer funID) {
		return tbUserDao.findFunnameById(funID);
	}

	public PageModel findAllProblem(String problemName){
		return tbUserDao.findAllProblem(problemName);
	}

	public String saveProblem(String problemName) {
		tbUserDao.saveProblem(problemName);
		return "success";
	}

	@SuppressWarnings("unchecked")
	public Map findProblemById(Integer problemId) {
		
		return tbUserDao.findProblemById(problemId);
	}

	public String updateProblem(String problemName, Integer problemId) {
		tbUserDao.updateProblem(problemName, problemId);
		return "success";
	}

	public String deleteProblemById(Integer problemId) {
		tbUserDao.deleteProblemById(problemId);
		return "success";
		
	}

	@SuppressWarnings("unchecked")
	public List findArea(String areaName,Integer areaId) {
		return tbUserDao.findArea(areaName,areaId);
	}

	public String saveArea(String areaName) {
		tbUserDao.saveArea(areaName);
		return "seccess";
	}

	public String updateArea(String areaName, Integer areaId) {
		tbUserDao.updateArea(areaName, areaId);
		return "seccess";
	}

	@Override
	public PageModel findAllCustom(String area,String name) {
		return tbUserDao.findAllCustom(area,name);
	}

	@Override
	public void deleteCustom(int id) {
		tbUserDao.deleteCustom(id);
	}

	@Override
	public void saveCustom(String name, String tel, String py, String bz) {
		tbUserDao.saveCustom(name, tel, py, bz);
	}

	@Override
	public Map findCustomById(int id) {
		return tbUserDao.findCustomById(id);
	}

	@Override
	public void updateCustom(int id, String name, String tel, String area,String py, String bz) {
		tbUserDao.updateCustom(id, name, tel, area, py, bz);
	}

	@Override
	public int findCustomName(String name) {
		return tbUserDao.findCustomName(name);
	}
}
