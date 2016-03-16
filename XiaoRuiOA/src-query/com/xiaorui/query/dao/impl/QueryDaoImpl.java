package com.xiaorui.query.dao.impl;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.util.AbstractDao;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.query.dao.QueryDao;

public class QueryDaoImpl extends AbstractDao implements QueryDao{

	public PageModel findReceiptRec() {
		String sql="select * from tb_receipt";
		return findPageModel(sql);
	}

	public List<Map<String,Object>> findAllArea() {
		String sql="select * from tb_area";
		return getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List findRepairRecByArea(Integer areaId,String time) {
		String sql="select t1.*,t2.pro_name,t2.pro_num,t2.pro_area_id from tb_repair_rec t1,tb_product t2 where t1.product_id=t2.pro_id and t2.pro_area_id=?";
		if(time!=null&&!"".equals(time)){
			sql+=" and t1.repair_time like '%"+time+"%'";
		}
		return getJdbcTemplate().queryForList(sql,areaId);
	}

	public List<Map<String, Object>> findRepairRec(String condition,String time) {
		String sql=null;
		if(condition.equals("PERSON")){
			sql="select t.repair_person,count(t.repair_id) as count from tb_repair_rec t where 1=1";
			if(time!=null&&!"".equals(time)){
				sql+=" and t.repair_time like '%"+time+"%'";
			}
			sql+=" group by t.repair_person";
		}else if(condition.equals("PROBLEM")){
			sql="select t.repair_problem,count(t.repair_id) as count from tb_repair_rec t where 1=1";
			if(time!=null&&!"".equals(time)){
				sql+=" and t.repair_time like '%"+time+"%'";
			}
			sql+=" group by t.repair_problem order by count desc";
		}
		return getJdbcTemplate().queryForList(sql);
	}

}
