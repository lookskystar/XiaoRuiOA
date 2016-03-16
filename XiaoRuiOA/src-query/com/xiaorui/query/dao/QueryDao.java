package com.xiaorui.query.dao;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.util.PageModel;

public interface QueryDao {
	
	/**
	 * 查询收货记录
	 * @return
	 */
	public PageModel findReceiptRec();
	
	/**
	 *  查询所有的地区
	 * @return
	 */
	public List<Map<String,Object>> findAllArea();
	
	/**
	 * 根据地区ID查询产品维修记录信息
	 * @param areaId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findRepairRecByArea(Integer areaId,String time);
	
    /**
     * 根据条件查询检修记录信息
     * @param condition
     * @return
     */
	public List<Map<String,Object>> findRepairRec(String condition,String time);

}
