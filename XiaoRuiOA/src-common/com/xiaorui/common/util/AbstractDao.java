package com.xiaorui.common.util;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 分页处理的业务类
 * @author yangmin
 *
 */
public class AbstractDao extends JdbcDaoSupport{

    /**
	 * 联表分页查询时SQL语句的参数为一个数组
	 * @param sql SQl语句
	 * @param args 参数数组
	 * @return
	 */
	public PageModel findPageModel(String sql,Object... args){
		PageModel pm = new PageModel();  
		int count = getJdbcTemplate().queryForInt(getCountSql(sql), args);
		pm.setCount(count);
		sql=getPageSQL(sql, SystemContext.getOffset(), SystemContext.getPageSize());
		List<?> datas =getJdbcTemplate().queryForList(sql, args);
		pm.setDatas(datas);
		return pm;
	}
	
	/**
	 * 根据SQL语句得出所有的记录条数
	 * @param sql
	 * @return String
	 */
	protected String getCountSql(String sql){
		return "select count(*) "+sql.substring(sql.indexOf("from"));
	}
	
	/**
	 * 构造数据分页SQL
	 * @param queryString
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	protected String getPageSQL(String queryString,Integer startIndex,Integer pageSize){
		String result = "";
		if (null != startIndex && null !=pageSize ) {
			result=queryString + " limit "+startIndex + "," +pageSize;
		}else if (null != startIndex && null == pageSize) {
			result = queryString + " limit " + startIndex;
		}else{
			result = queryString;
		}
		return  result;
	}
}
