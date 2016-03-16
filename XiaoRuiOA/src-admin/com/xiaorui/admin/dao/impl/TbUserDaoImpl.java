package com.xiaorui.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.xiaorui.common.util.AbstractDao;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.admin.dao.TbUserDao;

public class TbUserDaoImpl extends AbstractDao implements TbUserDao{
	
	@SuppressWarnings("unchecked")
	public Map login(String username, String password) {
		String sql= "select u.* from tb_user u where u.user_name=? and u.user_pwd=?";
		Map user=null;
		try {
			user = getJdbcTemplate().queryForMap(sql, new Object[]{username,password});
		} catch (DataAccessException e) {
			user=null;
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List getFunPrivsByRole(Integer roleid) {
		String sql="select tf.* from tb_function tf,tb_role_function trf where tf.func_id=trf.func_id and trf.role_id=?";
        return getJdbcTemplate().queryForList(sql,roleid);
	}	

	
	@SuppressWarnings("unchecked")
	public Map findFunnameById(Integer funID){
			String sql = "select f.func_name from tb_Function f where f.func_Id=?";
			return getJdbcTemplate().queryForMap(sql,funID);
	}	
	
	public PageModel findAllProblem(String problemName){
		StringBuilder builder = new StringBuilder();
			builder.append("select * from tb_problem where 1=1");
			List<Object> params = new ArrayList<Object>();
			if(problemName !=null &&!problemName.equals("")){
				builder.append(" and problem_content like ?");
				params.add("%"+problemName+"%");
			}
			builder.append(" order by problem_id");
		return findPageModel(builder.toString(),params.toArray());
	}
	
	public void saveProblem(String problem_name){
		String sql ="insert into tb_problem (problem_content) value(?)";
		getJdbcTemplate().update(sql, problem_name);
	}
	
	@SuppressWarnings("unchecked")
	public Map findProblemById(Integer problemId){
		String sql ="select * from tb_problem where problem_id =?";
		return getJdbcTemplate().queryForMap(sql, problemId);
	}
	
	public void updateProblem(String problemName,Integer problemId){
		String sql="update tb_problem t set t.problem_content=? where t.problem_id=?";
		try {
			getJdbcTemplate().update(sql, new Object[]{problemName,problemId });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProblemById(Integer problemId){
		String sql ="delete from tb_problem where problem_id=?";
		getJdbcTemplate().update(sql, problemId);
	}
	
	@SuppressWarnings("unchecked")
	public List findArea(String areaName,Integer areaId){
		StringBuilder builder=new StringBuilder();
		builder.append("select t.area_id,t.area_name from tb_area t where 1=1");
		List<Object> params=new ArrayList<Object>();
		if(areaName!=null&&!areaName.equals("")){
			builder.append("  and t.area_name like ?");
			params.add("%"+areaName+"%");
		}
		if(areaId!=null&&!areaId.equals("")){
			builder.append(" and t.area_id =?");
			params.add(areaId);
		}
		builder.append(" order by t.area_id");
		return getJdbcTemplate().queryForList(builder.toString(),params.toArray());
	}
	
	public void saveArea(String areaName){
		String sql ="insert into tb_area (area_name) value(?)";
		getJdbcTemplate().update(sql, areaName);
	}
	
	public void updateArea(String areaName,Integer areaId){
		String sql="update tb_area t set t.area_name=? where t.area_id=?";
		try {
			getJdbcTemplate().update(sql, new Object[]{areaName,areaId });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PageModel findAllCustom(String area,String name) {
		String sql = "select * from tb_mail where 1=1";
		if(area != null && !area.equals("")){
			sql += " and mail_area like '%"+area+"%'";
		}
		if(name != null && !name.equals("")){
			sql += " and mail_name like '%"+name+"%'";
		}
		return findPageModel(sql);
	}

	@Override
	public void deleteCustom(int id) {
		String sql = "delete from tb_mail where mail_id = " + id + "";
		getJdbcTemplate().update(sql);
	}

	@Override
	public void saveCustom(String name, String tel, String py, String bz) {
		String sql = "insert into tb_mail(mail_id,mail_name,mail_phone,mail_py,mail_comment) values(?,?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[] { null, name, tel, py, bz });
	}

	@Override
	public Map findCustomById(int id) {
		String sql = "select * from tb_mail where mail_id = " + id + "";
		return getJdbcTemplate().queryForMap(sql);
	}

	@Override
	public void updateCustom(int id, String name, String tel, String area,String py, String bz) {
		String sql = "update tb_mail set mail_name = '" + name
				+ "',mail_phone = '" + tel + "',mail_area = '" + area
				+ "',mail_py = '" + py + "',mail_comment = '" + bz
				+ "' where mail_id = " + id + "";
		getJdbcTemplate().update(sql);
	}

	@Override
	public int findCustomName(String name) {
		String sql = "select count(*) from tb_mail where mail_name='"+name+"'";
		return getJdbcTemplate().queryForInt(sql);
	}
}
