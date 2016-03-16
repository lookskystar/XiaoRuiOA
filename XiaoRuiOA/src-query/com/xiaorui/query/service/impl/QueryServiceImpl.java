package com.xiaorui.query.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaorui.common.bean.CountBean;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.query.dao.QueryDao;
import com.xiaorui.query.service.QueryService;

public class QueryServiceImpl implements QueryService{
	
	@Resource(name="queryDao")
	private QueryDao queryDao;

	public PageModel findReceiptRec() {
		return queryDao.findReceiptRec();
	}

	@SuppressWarnings("unchecked")
	public Map<String, Double> findRepairRecByArea(String time) {
		List<Map<String,Object>> areas=queryDao.findAllArea();
		Map<String,Double> map=new HashMap<String,Double>();
		List repairRecs=null;
		for(Map<String,Object> area:areas){
			repairRecs=queryDao.findRepairRecByArea((Integer)area.get("area_id"),time);
			String key=(String)area.get("area_name");
			Double value=0D;
			if(repairRecs!=null&&repairRecs.size()>0){
				value=Double.parseDouble(repairRecs.size()+"");
			}
			map.put(key, value);
		}
		return map;
	}

	public List<Map<String, Object>> findRepairRec(String condition,String time) {
		return queryDao.findRepairRec(condition,time);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CountBean> findCount(String time) {
		List<Map<String,Object>> areas=queryDao.findAllArea();
		CountBean countBean=null;
		List repairRecs=null;
		List<CountBean> list=new ArrayList<CountBean>();
		for(Map<String,Object> area:areas){
			countBean=new CountBean();
			countBean.setArea((String)area.get("area_name"));
			repairRecs=queryDao.findRepairRecByArea((Integer)area.get("area_id"),time);
			if(repairRecs!=null&&repairRecs.size()>0){
				countBean.setNum(Integer.parseInt(repairRecs.size()+""));
				countBean.setRepairRecs(repairRecs);
			}
			list.add(countBean);
		}
		return list;
	}
}
