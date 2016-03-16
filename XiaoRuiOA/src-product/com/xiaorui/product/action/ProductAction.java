package com.xiaorui.product.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xiaorui.common.util.Contains;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.product.service.ProductService;



public class ProductAction extends ActionSupport{

	private static final long serialVersionUID = 7161443061203175332L;
	@Resource(name="productService")
	private ProductService productService;
	
	private String name;
	private String num;
	private Integer status;
	private Integer areaId;
	private String beginTime;
	private String endTime;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
	public static final SimpleDateFormat YMDHM_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 进入产品履历
	 */
	public String productInput() throws Exception {
		request.setAttribute("arealist", productService.listArea());
		PageModel  pm=productService.getProduct(name, num, status, areaId, beginTime, endTime);
		request.setAttribute("pm", pm);
		return "product";
	}

	/**
	 * 查看产品详情
	 */
	@SuppressWarnings("unchecked")
	public String productInfo() throws Exception{
		Map map=productService.getProductById(Long.parseLong(request.getParameter("pro_id")));
		request.setAttribute("map", map);
		request.setAttribute("arealist", productService.listArea());
		return "productInfo";
	}
	
	/**
	 * 查询产品维修记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String resumeInput() throws Exception{
		List<Map<String,Object>> list= productService.findResume(Long.valueOf(request.getParameter("pro_id")));
		Map map=productService.getProductById(Long.parseLong(request.getParameter("pro_id")));
		request.setAttribute("map", map);
		request.setAttribute("resume", list);
		request.setAttribute("arealist", productService.listArea());
		return "resume";
	}
	
	/**
	 * 进入产品维修
	 */
	@SuppressWarnings("unchecked")
	public String repairInput() throws Exception{
		Long pro_id=Long.parseLong(request.getParameter("pro_id"));
		Map product=productService.getProductById(pro_id);
		List problems=productService.getProblems();
		request.setAttribute("ntime", YMD_FORMAT.format(new Date()));
		request.setAttribute("product", product);
		if(problems!=null&&problems.size()>0){
			request.setAttribute("problems", problems);
		}
		return "repair";
	} 
	
	/**
	 * 维修
	 */
	public String repair() throws Exception{
		Integer pro_id=Integer.parseInt(request.getParameter("pro_id"));
		String repair_person=request.getParameter("repair_person");
		String repair_time=request.getParameter("repair_time");
		String repair_problem=request.getParameter("repair_problem");
		String repair_deal=request.getParameter("repair_deal");
		String result="success";
		try {
			productService.saveRepairRec(pro_id, repair_person, repair_time, repair_problem, repair_deal);
		} catch (Exception e) {
			result="failure";
			e.printStackTrace();
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
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
	
	/**
	 * 进入更改产品履历
	 */
	@SuppressWarnings("unchecked")
	public String editProductInput() throws Exception{
		Map map=productService.getProductById(Long.parseLong(request.getParameter("pro_id")));
		request.setAttribute("arealist", productService.listArea());
		request.setAttribute("map", map);
		return "editProduct";
	} 

	/**
	 * 产品履历修改
	 */
	public String editProduct() throws Exception{
		String result="failure";
		Long proId=Long.parseLong(request.getParameter("pro_id"));
		String name = request.getParameter("pro_name");
		String num = request.getParameter("pro_num");
		String comeTime = request.getParameter("pro_come_time");
		String produceTime = request.getParameter("pro_produce_time");
		String type = request.getParameter("pro_type");
		String comment = request.getParameter("comment");
		Long areaId=Long.parseLong(request.getParameter("pro_area_id"));
		int NUM=productService.updateProduct(proId, name, num, comeTime, produceTime, type, comment,areaId);
		if(NUM>0){
			result = "success";
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}
	
	/**
	 * ajax根据产品编码判断产品是否存在
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public String ajaxProductExist() throws IOException{
		String pro_num=request.getParameter("pro_num");
		String result="failure";
		List list=productService.getProductByNum(pro_num);
		if(list!=null&&list.size()>0){
			result="success";
		}
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 进入产品支出(单个)
	 */
	@SuppressWarnings("unchecked")
	public String outProductInput() throws Exception{
		Map map=productService.getProductById(Long.parseLong(request.getParameter("pro_id")));
		request.setAttribute("map", map);
		request.setAttribute("arealist", productService.listArea());
		request.setAttribute("ntime", YMD_FORMAT.format(new Date()));
		return "outProduct";
	} 
	
	/**
	 * 产品支出(单个)
	 */
	public String outProduct() throws Exception{
		String result="failure";
		Long proid=Long.parseLong(request.getParameter("pro_id"));
		Integer areaId = Integer.parseInt(request.getParameter("pro_area_id"));
		String outTime = request.getParameter("pro_out_time");
		int NUM=productService.updateProduct(proid, areaId, outTime);
		if(NUM>0){
			result="success";
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}
	
	/**
	 * 进入产品支出(批量)
	 */
	public String outInput() throws Exception{
		String proIds= request.getParameter("proIds");
		request.setAttribute("arealist", productService.listArea());
		request.setAttribute("ntime", YMD_FORMAT.format(new Date()));
		request.setAttribute("proIds", proIds);
		return "out";
	} 
	
	/**
	 * 产品支出(批量)
	 */
	public String out() throws Exception{
		String proIds = request.getParameter("proIds");
		Integer areaId = Integer.parseInt(request.getParameter("pro_area_id"));
		String outTime = request.getParameter("pro_out_time");
		String proIdArray[] =proIds.split(",");
		String result="failure";
		int NUM=0;
		if(proIdArray.length>0){
		   	NUM=productService.updateProducts(proIdArray, areaId, outTime);
		}
		if(NUM>0){
			result="success";
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}
	
	public String delProduct()throws Exception{
		Integer proId=Integer.parseInt(request.getParameter("pro_id"));
		String result="success";
		try {
			productService.delProductById(proId);
		} catch (Exception e) {
			result="failure";
			e.printStackTrace();
		}
		response.getWriter().write(result);
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}
