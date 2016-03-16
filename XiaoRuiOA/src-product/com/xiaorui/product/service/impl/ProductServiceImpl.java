package com.xiaorui.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaorui.common.util.PageModel;
import com.xiaorui.product.dao.ProductDao;
import com.xiaorui.product.service.ProductService;


public class ProductServiceImpl implements ProductService{
	

	@Resource(name="productDao")
	private ProductDao productDao;
	public PageModel getProduct(String name, String num, Integer status,
			Integer areaId, String beginTime, String endTime) {
		return productDao.getProduct(name, num, status, areaId, beginTime, endTime);
	}

	@SuppressWarnings("unchecked")
	public List listArea() {
		return productDao.listArea();
	}

	@SuppressWarnings("unchecked")
	public Map getProductById(Long proid) {
		return productDao.getProductById(proid);
	}

	@SuppressWarnings("unchecked")
	public List findResume(Long proid) {
		return productDao.findResume(proid);
	}

	public void saveProduct(String name, String num, Integer status,
			Integer areaId, String comeTime, String produceTime, String type,
			String comment) {
		productDao.saveProduct(name, num, status, areaId, comeTime, produceTime, type, comment);
	}

	@SuppressWarnings("unchecked")
	public List getProductByNum(String num) {
		return productDao.getProductByNum(num);
	}

	public int updateProduct(Long proid, Integer areaId, String outTime) {
		int result=0;
		productDao.updateProduct(proid, areaId, outTime);
		result++;
		return result;
	}

	public int updateProducts(String[] proIdArray, Integer areaId, String outTime) {
		int num=0;
		for (int i = 0; i < proIdArray.length; i++) {
			productDao.outProduct(Long.parseLong(proIdArray[i]), areaId, outTime);
			num++;
		}
		return num;
	}

	public List getProblems() {
		return productDao.getProblems();
	}

	public void saveRepairRec(Integer product_id,String repair_person,String repair_time,String repair_problem,String repair_deal) {
		productDao.saveRepairRec(product_id, repair_person, repair_time, repair_problem, repair_deal);
		productDao.updateProductStatus(product_id, 0);//将产品信息修改为良好
	}

	public void delProductById(Integer proId) {
		productDao.delProductById(proId);
	}

	@Override
	public int updateProduct(Long proId, String name, String num,
			String comeTime, String produceTime, String type, String comment,Long areaId) {
		int number=0;
		productDao.updateProduct(proId, name, num, comeTime, produceTime, type, comment,areaId);
		number++;
		return number;
	}
	
}
