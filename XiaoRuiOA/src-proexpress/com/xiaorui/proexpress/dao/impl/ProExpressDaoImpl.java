package com.xiaorui.proexpress.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.xiaorui.common.bean.BackRec;
import com.xiaorui.common.bean.Receipt;
import com.xiaorui.common.bean.Send;
import com.xiaorui.common.util.AbstractDao;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.proexpress.dao.ProExpressDao;

public class ProExpressDaoImpl extends AbstractDao implements ProExpressDao {

	public PageModel findReceipts(int send_area, String express_num, String btime, String etime, int type) {
		String sql = "select * from tb_receipt t,tb_area b where t.send_area = b.area_id";
		if (send_area != 0) {
			sql += " and t.send_area = " + send_area + "";
		}
		if (express_num != null && !express_num.equals("")) {
			sql += " and t.express_num = '" + express_num + "'";
		}
		if (btime != null && !btime.equals("") && etime != null	&& !etime.equals("")) {
			sql += " and t.receive_time >= '" + btime + "' and t.receive_time <= '" + etime + "'";
		}
		if (type == 0) {
			sql += " order by t.receive_time desc";
		} else {
			sql += " and t.receipt_status != 2 order by t.receive_time asc";
		}
		return findPageModel(sql);
	}

	@SuppressWarnings("unchecked")
	public List findAllAreas() {
		String sql = "select * from tb_area t where t.area_id != 99";
		return getJdbcTemplate().queryForList(sql);
	}

	public void saveReceipt(Receipt receipt) {
		String sql = "insert into tb_receipt(receipt_id,send_area,express_num,express_money,express_person,express_phone,receive_time,product_count,product_nums,comment,is_express,receipt_status,send_count) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(sql,
				new Object[] { null, receipt.getSend_area(),
						receipt.getExpress_num(), receipt.getExpress_money(),
						receipt.getExpress_person(),
						receipt.getExpress_phone(), receipt.getReceive_time(),
						receipt.getProduct_count(), receipt.getProduct_nums(),
						receipt.getComment(), receipt.getIs_express(),
						receipt.getReceipt_status(), receipt.getSend_count() });
	}

	public Integer saveSend(final Send send) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				String sql = "insert into tb_send(send_express_num,send_express_company,send_express_money,send_person,send_time,receive_time,send_product_num,receive_area,comment,is_express,send_count) values (?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql,	Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, send.getSend_express_num());
				ps.setString(2, send.getSend_express_company());
				ps.setInt(3, send.getSend_express_money());
				ps.setString(4, send.getSend_person());
				ps.setString(5, send.getSend_time());
				ps.setString(6, send.getReceive_time());
				ps.setString(7, send.getSend_product_num());
				ps.setString(8, send.getReceive_area());
				ps.setString(9, send.getComment());
				ps.setInt(10, send.getIs_express());
				ps.setInt(11, send.getSend_count());
				return ps;
			}
		}, keyHolder);
		Integer generatedId = keyHolder.getKey().intValue();
		return generatedId;
	}

	public Map findReceiptById(int receiptId) {
		String sql = "select * from tb_receipt t,tb_area b where t.send_area = b.area_id and t.receipt_id = " + receiptId	+ "";
		return getJdbcTemplate().queryForMap(sql);
	}

	public void saveBackRec(BackRec backRec) {
		String sql = "insert into tb_back_rec(back_id,back_time,back_area,back_person,back_phone,back_situation,receipt_id,do_person) values (?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(sql,
				new Object[] { null, backRec.getBack_time(),
						backRec.getBack_area(), backRec.getBack_person(),
						backRec.getBack_phone(), backRec.getBack_situation(),
						backRec.getReceipt_id(), backRec.getDo_person()});
	}

	@SuppressWarnings("unchecked")
	public List findBackRecById(int receiptId) {
		String sql = "select * from tb_back_rec where receipt_id = " + receiptId + "";
		return getJdbcTemplate().queryForList(sql);
	}

	public void updateReceipt(int receiptId, int receiptStatus, int sendCount) {
		String sql = "update tb_receipt set receipt_status = " + receiptStatus + " ,send_count = " + sendCount + " where receipt_id = " + receiptId + "";
		getJdbcTemplate().update(sql);
	}

	public void saveReceiptSend(int receiptId, int sendId) {
		String sql = "insert into tb_receipt_send(rs_id,receipt_id,send_id) values (?,?,?)";
		getJdbcTemplate().update(sql, new Object[] { null, receiptId, sendId });
	}

	public List findSendRec(int receiptId) {
		String sql = "select * from tb_send b ,tb_area t where b.receive_area = t.area_id and b.send_id in (select t.send_id from tb_receipt_send t where t.receipt_id = " + receiptId + ")";
		return getJdbcTemplate().queryForList(sql);
	}

	public PageModel findBackRec(int sendArea, String btime, String etime) {
		String sql = "select * from tb_back_rec t,tb_area b where t.back_area = b.area_id";
		if (sendArea != 0) {
			sql += " and t.back_area = " + sendArea + "";
		}
		if (btime != null && !btime.equals("") && etime != null	&& !etime.equals("")) {
			sql += " and t.back_time >= '" + btime + "' and t.back_time <= '" + etime + "'";
		}
		sql += " order by t.back_time desc";
		return findPageModel(sql);
	}

	public int findProNum(String num, int area_id ,String type) {
		String sql = "";
		if(type.equals("1")){
			sql = "select count(*) from tb_product t where t.pro_num = '"+num+"' and t.pro_area_id = 99 and t.pro_status = 0";
		}else{
			sql = "select count(*) from tb_product t where t.pro_num = '"+num+"' and t.pro_area_id = "+area_id+" and t.pro_status = 2";
		}
		return getJdbcTemplate().queryForInt(sql);
	}

	public void updateProduct(String num, int type ,int area_id) {
		String sql = null;
		if(type == 0){
			sql = "update tb_product t set t.pro_area_id = 99 ,t.pro_status = 1 where t.pro_num = '"+num+"'";
		}else{
			sql = "update tb_product t set t.pro_area_id = "+area_id+" ,t.pro_status = 2 where t.pro_num = '"+num+"'";
		}
		getJdbcTemplate().update(sql);
	}

	@Override
	public List findMailName(String keyQuery) {
		String sql = "select mail_name from tb_mail where mail_py like '%"+keyQuery+"%'";
		return getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map findPhone(String name) {
		String sql = "select mail_phone from tb_mail where mail_name='"+name+"'";
		return getJdbcTemplate().queryForMap(sql);
	}
}
