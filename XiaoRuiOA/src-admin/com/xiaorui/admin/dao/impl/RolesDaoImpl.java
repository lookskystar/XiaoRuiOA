package com.xiaorui.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xiaorui.admin.dao.RolesDao;
import com.xiaorui.common.util.AbstractDao;

public class RolesDaoImpl extends AbstractDao implements RolesDao {

	public void saveRole(String roleName,String rolePy,Integer roleLevel) {
		String sql ="insert into tb_role (role_name,role_py,role_level) values(?,?,?)";
		getJdbcTemplate().update(sql,new Object[]{roleName,rolePy,roleLevel});
	}


	public void updateRole(Integer roleId,String roleName,String rolePy,Integer roleLevel) {
		StringBuilder builder = new StringBuilder();
        builder.append("update tb_role r set");
        List<Object> params = new ArrayList<Object>();
        if(roleName!=null&&!roleName.equals("")){
        	builder.append(" r.role_name=?,");
        	params.add(roleName);
        }
        if(rolePy!=null&&!rolePy.equals("")){
        	builder.append(" r.role_py=?,");
        	params.add(rolePy);
        }
        if(roleLevel!=null&&!roleLevel.equals("")){
        	builder.append(" r.role_level=? ");
        	params.add(roleLevel);
        }
        builder.append(" where r.role_id=?");
        params.add(roleId);
        getJdbcTemplate().update(builder.toString(),params.toArray());
	}
	
	public void deleteRole(Integer roleID) {
		String sql = "delete from tb_role where role_id=?";
		getJdbcTemplate().update(sql,roleID);
	}

	
	public long countUsers(Integer roleID) {
		String sql = "select count(*) from  tb_user where user_role_id =?";
		return getJdbcTemplate().queryForLong(sql, roleID);
	}

	
	@SuppressWarnings("unchecked")
	public Map getTbRolesById(Integer roleID) {
		String sql = "select * from tb_role where role_id=?";
		return getJdbcTemplate().queryForMap(sql, roleID);
	}


	@SuppressWarnings("unchecked")
	public List getTbRolesByName(String roleName) {
		String sql = "select d.* from tb_role d where d.role_name=?";
		List list = getJdbcTemplate().queryForList(sql, roleName);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	public List listRoles() {
		String sql = "select * from tb_role order by role_id";
		return getJdbcTemplate().queryForList(sql);
	}


//	@SuppressWarnings("unchecked")
//	public List<DictFunctions> listMainFunctionPrivs() {
//		String hql = "from DictFunctions where parentId is null ";
//		return getHibernateTemplate().find(hql);
//	}


	@SuppressWarnings("unchecked")
	public List listSecFunctionPrivs() {
		String sql = "select * from tb_function where func_parentid is  not null order by func_id asc";
		return getJdbcTemplate().queryForList(sql);
	}


	@SuppressWarnings("unchecked")
	public Map findFunnameById(Integer parentid) {
		String sql = "select f.func_name from tb_function f where f.func_id=?";
		return getJdbcTemplate().queryForMap(sql, parentid);
	}


	public void saveRoleFunPrivs(Integer roleID,Integer s) {
		String sql ="insert into tb_role_function (role_id,func_id) values (?,?)";
		getJdbcTemplate().update(sql,new Object[]{roleID,s});
		
	}


	public void deleteRoleFunPrivs(Integer roleID) {
		String sql = "delete from tb_role_function where role_id=?";
		getJdbcTemplate().update(sql,roleID);
		
	}


	@SuppressWarnings("unchecked")
	public List getRolesFunctionById(Integer roleID) {
		String sql="select tf.* from tb_function tf,tb_role_function trf where tf.func_id=trf.func_id and trf.role_id=?";
		return getJdbcTemplate().queryForList(sql,roleID);
	}

	
}
