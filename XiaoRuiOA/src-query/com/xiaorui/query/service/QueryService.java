package com.xiaorui.query.service;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.bean.CountBean;
import com.xiaorui.common.util.PageModel;

public interface QueryService {
	
	/**
	 * 查询收货记录
	 * @return
	 */
	public PageModel findReceiptRec();
	
	/**
	 * 根据地区ID查询产品维修记录信息
	 * @param areaId
	 * @return
	 */
	public Map<String,Double> findRepairRecByArea(String time);
	
    /**
     *根据条件查询检修记录信息
     * @param condition
     * @return
     */
	public List<Map<String,Object>> findRepairRec(String condition,String time);
	
	public List<CountBean> findCount(String time);
	
}
