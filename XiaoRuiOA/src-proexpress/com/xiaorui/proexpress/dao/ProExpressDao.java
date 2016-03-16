package com.xiaorui.proexpress.dao;

import java.util.List;
import java.util.Map;

import com.xiaorui.common.bean.BackRec;
import com.xiaorui.common.bean.Receipt;
import com.xiaorui.common.bean.Send;
import com.xiaorui.common.util.PageModel;

public interface ProExpressDao {

	/**
	 * 查询收货信息
	 * */
	public PageModel findReceipts(int send_area, String express_num, String btime, String etime, int type);

	/**
	 * 查询所有单位
	 * */
	public List findAllAreas();

	/**
	 * 保存收件单
	 * */
	public void saveReceipt(Receipt receipt);

	/**
	 * 保存发件单
	 * */
	public Integer saveSend(Send send);

	/**
	 * 根据id查找收件单
	 * */
	public Map findReceiptById(int receipt_id);

	/**
	 * 保存回访记录
	 * */
	public void saveBackRec(BackRec backRec);

	/**
	 * 根据receipt_id查找回访记录
	 * */
	public List findBackRecById(int receipt_id);

	/**
	 * 跟新收件单
	 * */
	public void updateReceipt(int receipt_id, int receipt_status, int send_count);

	/**
	 * 保存收发件关联
	 * */
	public void saveReceiptSend(int receipt_id, int send_id);

	/**
	 * 查询发件记录
	 * */
	public List findSendRec(int receipt_id);

	/**
	 * 查询回访记录
	 * */
	public PageModel findBackRec(int send_area, String btime, String etime);
	
	/**
	 * 判断设备是否存在
	 * */
	public int findProNum(String num, int area_id,String type);
	
	/**
	 * 修改产品状态
	 * */
	public void updateProduct(String num, int type,int area_id);
	
	/**
	 * 拼音检索发件人
	 * */
	@SuppressWarnings("unchecked")
	public List findMailName(String keyQuery);
	
	/**
	 * 根据发件人姓名查询电话
	 * */
	@SuppressWarnings("unchecked")
	public Map findPhone(String name);
}
