package com.xiaorui.test.dao.impl;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.util.AbstractDao;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.test.dao.TestDao;

/**
 * 测试Dao类的实现类
 * 直接使用sql语句操作数据库
 * @author dell
 *
 */
public class TestDaoImpl extends AbstractDao implements TestDao{

	public void saveUser(String username, String password, String sex) {
		String sql="insert into tb_test(username,password,sex) values (?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{username,password,sex});
		
	}

	public void delUserById(Long id) {
		String sql="delete from tb_test where id=?";
		getJdbcTemplate().update(sql, id);
	}

	@SuppressWarnings("unchecked")
	public Map getUserById(Long id) {
		String sql="select * from tb_test where id=?";
		return getJdbcTemplate().queryForMap(sql, id);
	}

	@SuppressWarnings("unchecked")
	public List getUserByName(String name) {
		String sql="select * from tb_test where name like ?";
		return getJdbcTemplate().queryForList(sql, "%"+name+"%");
	}

	public PageModel getUserBySex(String sex) {
		String sql="select * from tb_test where sex=?";
		return findPageModel(sql, sex);
	}

	public void updateUserName(Long id, String name) {
		String sql="update tb_test set name=? where id=?";
		getJdbcTemplate().update(sql, new Object[]{name,id});
	}
}
