package com.xiaorui.proexpress.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaorui.common.bean.BackRec;
import com.xiaorui.common.bean.Receipt;
import com.xiaorui.common.bean.Send;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.proexpress.dao.ProExpressDao;
import com.xiaorui.proexpress.service.ProExpressService;

public class ProExpressServiceImpl implements ProExpressService {

	@Resource(name = "proExpressDao")
	private ProExpressDao proExpressDao;

	public PageModel findReceipts(int send_area, String express_num, String btime, String etime, int type) {
		return proExpressDao.findReceipts(send_area, express_num, btime, etime,	type);
	}

	public List findAllAreas() {
		return proExpressDao.findAllAreas();
	}

	public void saveReceipt(Receipt receipt) {
		proExpressDao.saveReceipt(receipt);
		String pro_nums = receipt.getProduct_nums();
		String[] nums =  pro_nums.split(",");
		for(String str : nums){
			proExpressDao.updateProduct(str, 0 ,99);
		}
	}

	public String updateStatus(Send send, String receipts) {
		String[] receiptIds = receipts.split(",");// 从页面中获得切割成字符串数组
		Integer sendId = proExpressDao.saveSend(send);// 保存发送表后获得的主键ID
		String pro_nums = send.getSend_product_num();
		int area_id = Integer.parseInt(send.getReceive_area());
		String[] nums =  pro_nums.split(",");
		
		for(String str : nums){
			proExpressDao.updateProduct(str, 1 ,area_id);
		}

		int sendCount = send.getSend_count();// 现在发送的数量
		int existCount = send.getSend_count();// 剩余要发送的数量
		for (String str : receiptIds) {// 遍历每条数据
			// 获得Receipt表的对象
			Map receipt = proExpressDao.findReceiptById(Integer.parseInt(str));
			proExpressDao.saveReceiptSend(Integer.parseInt(str), sendId);
			// saveReceiptSend(receiptIds,sendId);保存收货表、发送表关联关系
			// 得到Receipt表中收货表中的数量receiptCount
			int receiptCount = Integer.parseInt(receipt.get("product_count") + "");
			int receiptSendCount = Integer.parseInt(receipt.get("send_count") + "");
			if (receiptCount <= existCount + receiptSendCount) {
				// 更新receipt状态为全部发送完全，并且更新发货数量sendCount=receiptCount
				proExpressDao.updateReceipt(Integer.parseInt(str), 2, receiptCount);
				existCount -= receiptCount - receiptSendCount;
			} else {
				if (existCount == 0) {
					// 更新receipt状态为一个未发送
					proExpressDao.updateReceipt(Integer.parseInt(str), 0, 0);
				} else {
					// 更新receipt状态为未发送完全,并且更新发货数量为existCount
					proExpressDao.updateReceipt(Integer.parseInt(str), 1, existCount + receiptSendCount);
					existCount = 0;
				}
			}
		}
		return null;
	}

	public void saveBackRec(BackRec backRec) {
		proExpressDao.saveBackRec(backRec);
	}

	public List findBackRecById(int receiptId) {
		return proExpressDao.findBackRecById(receiptId);
	}

	public Map findReceiptById(int receiptId) {
		return proExpressDao.findReceiptById(receiptId);
	}

	public List findSendRec(int receiptId) {
		return proExpressDao.findSendRec(receiptId);
	}

	public PageModel findBackRec(int sendArea, String btime, String etime) {
		return proExpressDao.findBackRec(sendArea, btime, etime);
	}

	public int findProNum(String num, int area_id, String type) {
		return proExpressDao.findProNum(num, area_id, type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List findMailName(String keyQuery) {
		return proExpressDao.findMailName(keyQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map findPhone(String name) {
		return proExpressDao.findPhone(name);
	}
}
