package com.xiaorui.admin.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xiaorui.admin.dao.UsersDao;
import com.xiaorui.common.util.AbstractDao;
import com.xiaorui.common.util.PageModel;

public class UsersDaoImpl extends AbstractDao implements
		UsersDao {
	
	@SuppressWarnings("unchecked")
	public List findAllDept(){
		String sql = "select * from tb_dept";
		return getJdbcTemplate().queryForList(sql);
	}
	
	@SuppressWarnings("unchecked")
	public List findAllRoles(){
		String sql = "select * from tb_role";
		return getJdbcTemplate().queryForList(sql);
	}

	public long countUser(Integer dept_id) {
		String sql = "select count(*) from tb_user u where u.user_dept_id=?";
		return getJdbcTemplate().queryForLong(sql,dept_id);
	}

	public void delDept(Integer dept_id) {
		String sql = "delete from tb_dept where dept_id=?";
		getJdbcTemplate().update(sql,dept_id);
	}

	public void delTbUsers(Integer userId) {
		String sql = "delete from tb_user where user_id=?";
		getJdbcTemplate().update(sql,userId);
	}

	public PageModel findUsers(String name,Integer dept_id,Integer user_role_id) {
		StringBuilder builder=new StringBuilder();
		builder.append("select t.* from tb_user t where 1=1");
		List<Object> params=new ArrayList<Object>();
		if(name!=null&&!name.equals("")){
			builder.append("  and t.user_xm like ?");
			params.add("%"+name+"%");
		}
		if(user_role_id!=null&&!user_role_id.equals("")){
			builder.append("  and t.user_role_id =?");
			params.add(user_role_id);
		}
		if(dept_id!=null&&!dept_id.equals("")){
			builder.append("  and t.user_dept_id = ?");
			params.add(dept_id);
		}
		builder.append(" order by t.user_id");
		return findPageModel(builder.toString(),params.toArray());
	}

	public Map getDeptById(Integer dept_id) {
		String sql = "select * from tb_dept where dept_id =?";
		return getJdbcTemplate().queryForMap(sql, dept_id);
	}

	@SuppressWarnings("unchecked")
	public List getDeptByName(String bmName) {
		String sql = "select d.* from tb_dept d where d.dept_name=?";
		List list=getJdbcTemplate().queryForList(sql,bmName);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map getTbUsersById(Integer userId) {
		String sql ="select * from tb_user where user_id =?";
		return getJdbcTemplate().queryForMap(sql, userId);
	}

	public void saveDept(String dept_name) {
		String sql = "insert into tb_dept(dept_name) values (?)";
		getJdbcTemplate().update(sql,dept_name);
	}

	public void saveUsers(Integer user_role_id,Integer user_dept_id,
			String user_xm,String user_name,String user_phone,String user_pwd) {
		String sql = "insert into tb_user(user_role_id,user_dept_id,user_xm,user_name,user_phone,user_pwd) values (?,?,?,?,?,?)";
		getJdbcTemplate().update(sql,new Object[]{user_role_id,user_dept_id,user_xm,user_name,user_phone,user_pwd});
	}

	public void updateDept(Integer dept_id,String dept_name) {
		String sql = "update tb_dept t set t.dept_name =? where t.dept_id =?";
		getJdbcTemplate().update(sql,new Object[]{dept_name,dept_id});
		
	}

	public void updateTbUser(Integer user_id,Integer role_id,Integer depe_id,String user_xm,
			String user_phone,String user_name,String user_pwd) {
		String sql ="update tb_user set user_role_id=?,user_dept_id=?,user_xm=?,user_phone=?,user_name=?,user_pwd=? where user_id=?";
		getJdbcTemplate().update(sql, new Object[]{role_id,depe_id,user_xm,user_phone,user_name,user_pwd,user_id});
	}

}
