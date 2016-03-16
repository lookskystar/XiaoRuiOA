package com.xiaorui.product.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xiaorui.common.util.AbstractDao;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.product.dao.ProductDao;

public class ProductDaoImpl extends AbstractDao implements ProductDao{

	@SuppressWarnings("unchecked")
	public List listArea() {
		String sql="select * from tb_area order by area_id";
		return getJdbcTemplate().queryForList(sql);
	}

	public PageModel getProduct(String name, String num, Integer status,
			Integer areaId, String beginTime, String endTime) {
		StringBuilder builder=new StringBuilder();
		builder.append("select * from tb_product r where 1=1");
		List<Object> params=new ArrayList<Object>();
		if(name!=null&&!name.equals("")){
			builder.append(" and r.pro_name like ?");
			params.add("%"+name+"%");
		}
		if(num!=null&&!num.equals("")){
			builder.append(" and r.pro_num like ?");
			params.add("%"+num+"%");
		}
		if(status!=null&&!status.equals("")){
			builder.append(" and r.pro_status = ?");
			params.add(status);
		}
		if(areaId!=null&&!areaId.equals("")){
			builder.append(" and r.pro_area_id = ?");
			params.add(areaId);
		}
		if(beginTime!=null&&!beginTime.equals("")){
			builder.append(" and r.pro_produce_time >= ?");
			params.add(beginTime);
		}
		if(endTime!=null&&!endTime.equals("")){
			builder.append(" and r.pro_produce_time <= ?");
			params.add(endTime);
		}
		builder.append("  order by r.pro_produce_time desc");
		return findPageModel(builder.toString(),params.toArray());
	}

	@SuppressWarnings("unchecked")
	public Map getProductById(Long proid) {
		String sql="select * from tb_product where pro_id=?";
		return getJdbcTemplate().queryForMap(sql, proid);
	}

	@SuppressWarnings("unchecked")
	public List findResume(Long proid) {
		String sql="select  p.pro_name,p.pro_num,p.pro_type,p.pro_produce_time,r.repair_person,r.repair_problem,r.repair_deal,r.repair_time,r.comment from tb_product  p" +
				"  left join tb_repair_rec r  on p.pro_id=r.product_id"+
                "  where p.pro_id=?  order by r.repair_time";
        return getJdbcTemplate().queryForList(sql, proid);
	}

	public void saveProduct(String name, String num, Integer status,
			Integer areaId, String comeTime, String produceTime, String type,
			String comment) {
		String sql="insert into tb_product(pro_name,pro_num,pro_status,pro_area_id,pro_come_time,pro_produce_time,pro_type,comment) values (?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{name,num,status,areaId,comeTime,produceTime,type,comment});
	}

	@Override
	public void updateProduct(Long proId, String name, String num,
			String comeTime, String produceTime, String type, String comment,Long areaId) {
		String sql="update tb_product set pro_name=? ,pro_num=?,pro_come_time=?,pro_produce_time=?,pro_type=?,comment=?,pro_area_id=? where pro_id=?";
		getJdbcTemplate().update(sql, new Object[]{name,num,comeTime,produceTime,type,comment,areaId,proId});
		
	}
	
	@SuppressWarnings("unchecked")
	public List getProductByNum(String num) {
		String sql="select * from tb_product where pro_num = ?";
		List list=getJdbcTemplate().queryForList(sql, num);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public void updateProduct(Long proid,Integer areaId,String outTime) {
		String sql="update tb_product set pro_status=2,pro_area_id=?,pro_out_time=? where pro_id=?";
		getJdbcTemplate().update(sql, new Object[]{areaId,outTime,proid});
		
	}

	public void outProduct(Long proid, Integer areaId, String outTime) {
		String sql="update tb_product set pro_status=2,pro_area_id=?,pro_out_time=? where pro_id=?";
		getJdbcTemplate().update(sql, new Object[]{areaId,outTime,proid});
		
	}

	public List getProblems() {
		String sql="select * from tb_problem";
		return getJdbcTemplate().queryForList(sql);
	}

	public void saveRepairRec(Integer product_id,String repair_person,String repair_time,String repair_problem,String repair_deal) {
		String sql="insert into tb_repair_rec(product_id,repair_person,repair_time,repair_problem,repair_deal) values(?,?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{product_id,repair_person,repair_time,repair_problem,repair_deal});
	}

	public void updateProductStatus(Integer productId, Integer status) {
		String sql="update tb_product t set t.pro_status=? where t.pro_id=?";
		getJdbcTemplate().update(sql, new Object[]{status,productId});
	}

	public void delProductById(Integer proId) {
		String sql="delete from tb_product where pro_id=?";
		getJdbcTemplate().update(sql, proId);
	}

	
	
}
