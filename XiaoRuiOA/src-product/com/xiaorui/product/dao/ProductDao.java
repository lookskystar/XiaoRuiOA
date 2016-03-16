package com.xiaorui.product.dao;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.util.PageModel;


public interface ProductDao {
	
	/**
	 * 查询所有单位
	 */
	@SuppressWarnings("unchecked")
	public List listArea();
	
	/**
	 * 分页,查询产品信息
	 * @return
	 */
	public PageModel getProduct(String name,String num,Integer status,Integer areaId,String beginTime,String endTime);
	
	/**
	 * 通过id得到产品信息
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getProductById(Long proid);
	
	/**
	 * 维修记录
	 */
	
	@SuppressWarnings("unchecked")
	public List findResume(Long proid);
	
	/**
	 * 新产品入库
	 * */
	public void saveProduct(String name,String num,Integer status,Integer areaId,String comeTime,String produceTime,String type,String comment);
	
	/**
	 * 产品履历修改
	 * 可修改：产品名、编码、入库时间、生产时间、型号、编码、单位
	 * */
	public void updateProduct(Long proId,String name,String num,String comeTime,String produceTime,String type,String comment,Long areaId);
	
	/**
	 * 通过NUM获得产品信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getProductByNum(String num);
	
	/**
	 * 更改产品履历
	 * 出库(一个)，更改单位及出库时间
	 * @param id
	 */
	public void updateProduct(Long proid,Integer areaId,String outTime);
	
	/**
	 * 出库(批量)，更改单位及出库时间
	 * @param id
	 */
	public void outProduct(Long proid,Integer areaId,String outTime);
	
	/**
	 * 查询所有的故障信息
	 * @return
	 */
	public List getProblems();
	
	/**
	 * 保存维修记录信息
	 * @param params
	 */
	public void saveRepairRec(Integer product_id,String repair_person,String repair_time,String repair_problem,String repair_deal);
	
	/**
	 * 更新产品状态信息
	 * @param product_id
	 * @param status
	 */
	public void updateProductStatus(Integer product_id,Integer status);
	
	/**
	 * 删除产品信息
	 * @param proId
	 */
	public void delProductById(Integer proId);
	
	
}
