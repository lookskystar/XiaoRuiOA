package com.xiaorui.proexpress.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xiaorui.common.bean.BackRec;
import com.xiaorui.common.bean.Receipt;
import com.xiaorui.common.bean.Send;
import com.xiaorui.common.util.Contains;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.product.service.ProductService;
import com.xiaorui.proexpress.service.ProExpressService;

public class ProExpressAction extends ActionSupport {

	private static final long serialVersionUID = -8383187372510214477L;
	@Resource(name = "proExpressService")
	private ProExpressService proExpressService;
	@Resource(name = "productService")
	private ProductService productService;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	public static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private Receipt receipt;
	private Send send;
	private BackRec backRec;

	/**
	 * 进入收货列表页面
	 * */
	public String receiptListInput() {
		int area = 0;
		String send_area = request.getParameter("send_area");
		if (send_area != null && !send_area.equals("")) {
			area = Integer.parseInt(send_area);
		}
		String express_num = request.getParameter("express_num");
		String btime = request.getParameter("btime");
		String etime = request.getParameter("etime");

		PageModel receipts = proExpressService.findReceipts(area, express_num, btime, etime, 0);
		List areas = proExpressService.findAllAreas();
		request.setAttribute("areas", areas);
		request.setAttribute("receipts", receipts);
		request.setAttribute("send_area", area);
		request.setAttribute("express_num", express_num);
		request.setAttribute("btime", btime);
		request.setAttribute("etime", etime);
		return "receipt";
	}

	/**
	 * 进入收货单页面
	 * */
	@SuppressWarnings("unchecked")
	public String addReceiptInput() {
		List areas = proExpressService.findAllAreas();
		request.setAttribute("areas", areas);
		return "add_receipt";
	}

	/**
	 * 保存收货单
	 * */
	public String saveReceipt() {
		proExpressService.saveReceipt(receipt);
		request.setAttribute("message", "收货信息添加成功！");
		return receiptListInput();
	}

	/**
	 * 进入发货单页面
	 * */
	public String sendReceiptInput() {
		List areas = proExpressService.findAllAreas();
		request.setAttribute("areas", areas);
		return "send_receipt";
	}

	/**
	 * 进入选择收件单页面
	 * */
	public String checkReceiptInput() {
		int area_id = Integer.parseInt(request.getParameter("area_id"));
		PageModel receipts = proExpressService.findReceipts(area_id, null, null, null, 1);
		request.setAttribute("receipts", receipts);
		request.setAttribute("area_id",area_id);
		return "check_receipt";
	}

	/**
	 * 保存发件单
	 * */
	public String saveSend() {
		String receipt_ids = request.getParameter("receipt_num");
		proExpressService.updateStatus(send, receipt_ids);

		request.setAttribute("message", "发件成功！");
		return receiptListInput();
	}

	// 进入回访页面
	public String backRecInput() {
		int receipt_id = Integer.parseInt(request.getParameter("receipt_id"));
		String area_id = request.getParameter("area_id");
		List areas = proExpressService.findAllAreas();
		if (receipt_id != 0) {
			request.setAttribute("receipt_id", receipt_id);
			request.setAttribute("area_id", area_id);
		}
		request.setAttribute("areas", areas);
		return "back_rec";
	}

	// 保存回访
	public String saveBackRec() {
		proExpressService.saveBackRec(backRec);
		request.setAttribute("message", "回访记录添加成功！");
		return receiptListInput();
	}

	// 查看详情
	@SuppressWarnings( { "unchecked", "unchecked" })
	public String findDetail() {
		int receipt_id = Integer.parseInt(request.getParameter("receipt_id"));
		Map receipt = proExpressService.findReceiptById(receipt_id);
		List backRec = proExpressService.findBackRecById(receipt_id);
		List sendRec = proExpressService.findSendRec(receipt_id);

		request.setAttribute("sendRec", sendRec);
		request.setAttribute("receipt", receipt);
		request.setAttribute("backRec", backRec);
		return "detail";
	}

	// 进入回访记录
	public String backRecListInput() {
		int area = 0;
		String send_area = request.getParameter("send_area");
		if (send_area != null && !send_area.equals("")) {
			area = Integer.parseInt(send_area);
		}
		String btime = request.getParameter("btime");
		String etime = request.getParameter("etime");

		PageModel backRecs = proExpressService.findBackRec(area, btime, etime);
		List areas = proExpressService.findAllAreas();
		request.setAttribute("areas", areas);
		request.setAttribute("backRecs", backRecs);
		request.setAttribute("send_area", area);
		request.setAttribute("btime", btime);
		request.setAttribute("etime", etime);
		return "back_rec_list";
	}

	//判断设备状态
	public String checkNum() throws Exception{
		String num = request.getParameter("num");
		String type = request.getParameter("type");
		int area_id = Integer.parseInt(request.getParameter("area_id"));
		int count = proExpressService.findProNum(num, area_id ,type);
		
		response.setContentType("text/plain");
		if(count == 0){
			response.getWriter().write("failure");
		}else{
			response.getWriter().write("success");
		}
		return null;
	}
	
	//查看维修记录
	@SuppressWarnings("null")
	public String findRepeairRecByNum() {
		String pro_num = request.getParameter("pro_num");
		List products = productService.getProductByNum(pro_num);
		if (products != null && products.size() > 0) {
			Map map = (Map) products.get(0);
			long pro_id = Long.valueOf(map.get("pro_id")+"");
			request.setAttribute("resume", productService.findResume(pro_id));
			return "resume";
		}
		return null;
	}
	
	//获得发件人电话
	public String getPhone() throws Exception{
		String name = request.getParameter("name");
		Map phone = proExpressService.findPhone(name);
		String str = phone.get("mail_phone").toString();
		
		response.setContentType("text/plain");
		response.getWriter().write(str);
		return null;
	}
	
	/**
	 * 进入新产品入库
	 */
	public String addProductInput() throws Exception{
		request.setAttribute("arealist", productService.listArea());
		request.setAttribute("ntime", YMD_FORMAT.format(new Date()));
		return "addProduct";
	} 

	/**
	 * 产品入库
	 */
	public String saveProduct() throws Exception{
		String result="failure";
		String name = request.getParameter("pro_name");
		String num = request.getParameter("pro_num");
		Integer areaId=Integer.parseInt(request.getParameter("pro_area_id"));
		String comeTime = request.getParameter("pro_come_time");
		String produceTime = request.getParameter("pro_produce_time");
		String type = request.getParameter("pro_type");
		String comment = request.getParameter("comment");
		int NUM=0;
		if(areaId==Contains.COMPANY_AREA_ID){
			productService.saveProduct(name, num, 0, Contains.COMPANY_AREA_ID, comeTime, produceTime, type, comment); //新增产品状态都是[良好]
			NUM++;
		}else{
			productService.saveProduct(name, num, 2, areaId, comeTime, produceTime, type, comment); //新增已支出产品状态都是 [使用中]
			NUM++;
		}
		if(NUM>0){
			result = "success";
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}
	
	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Send getSend() {
		return send;
	}

	public void setSend(Send send) {
		this.send = send;
	}

	public BackRec getBackRec() {
		return backRec;
	}

	public void setBackRec(BackRec backRec) {
		this.backRec = backRec;
	}
}
