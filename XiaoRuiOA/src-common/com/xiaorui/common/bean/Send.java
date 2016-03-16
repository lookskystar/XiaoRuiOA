package com.xiaorui.common.bean;

public class Send {

	//主键
	private int send_id;
	//发货快递单号
	private String send_express_num;
	//发货快递公司
	private String send_express_company;
	//快递费用
	private int send_express_money;
	//发货人
	private String send_person;
	//发货时间
	private String send_time;
	//收货时间
	private String receive_time;
	//发货产品编码
	private String send_product_num;
	//收货单位
	private String receive_area;
	//备注
	private String comment;
	//是否用快递	0 不是快递	1 是快递
	private int is_express;
	//发货数量
	private int send_count;
	
	public int getSend_id() {
		return send_id;
	}
	public void setSend_id(int sendId) {
		send_id = sendId;
	}
	public String getSend_express_num() {
		return send_express_num;
	}
	public void setSend_express_num(String sendExpressNum) {
		send_express_num = sendExpressNum;
	}
	public String getSend_express_company() {
		return send_express_company;
	}
	public void setSend_express_company(String sendExpressCompany) {
		send_express_company = sendExpressCompany;
	}
	public int getSend_express_money() {
		return send_express_money;
	}
	public void setSend_express_money(int sendExpressMoney) {
		send_express_money = sendExpressMoney;
	}
	public String getSend_person() {
		return send_person;
	}
	public void setSend_person(String sendPerson) {
		send_person = sendPerson;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String sendTime) {
		send_time = sendTime;
	}
	public String getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(String receiveTime) {
		receive_time = receiveTime;
	}
	public String getSend_product_num() {
		return send_product_num;
	}
	public void setSend_product_num(String sendProductNum) {
		send_product_num = sendProductNum;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getSend_count() {
		return send_count;
	}
	public void setSend_count(int sendCount) {
		send_count = sendCount;
	}
	public String getReceive_area() {
		return receive_area;
	}
	public void setReceive_area(String receiveArea) {
		receive_area = receiveArea;
	}
	public int getIs_express() {
		return is_express;
	}
	public void setIs_express(int isExpress) {
		is_express = isExpress;
	}
}
