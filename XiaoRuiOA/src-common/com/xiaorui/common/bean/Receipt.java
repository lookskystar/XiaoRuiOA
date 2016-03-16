package com.xiaorui.common.bean;

public class Receipt {

	// id
	private int receipt_id;
	// 单位
	private int send_area;
	// 快递单号
	private String express_num;
	// 快递费用
	private int express_money;
	// 发件人
	private String express_person;
	// 联系方式
	private String express_phone;
	// 收件时间
	private String receive_time;
	// 数量
	private int product_count;
	// 产品编号
	private String product_nums;
	// 备注
	private String comment;
	// 是否属于快递 0是快递；1不是快递
	private int is_express;
	// 收货单状态 0一个未发；1发送不全；2全部发送
	private int receipt_status;
	// 已发货数量
	private int send_count;
	
	public int getReceipt_id() {
		return receipt_id;
	}

	public void setReceipt_id(int receiptId) {
		receipt_id = receiptId;
	}

	public int getSend_area() {
		return send_area;
	}

	public void setSend_area(int sendArea) {
		send_area = sendArea;
	}

	public String getExpress_num() {
		return express_num;
	}

	public void setExpress_num(String expressNum) {
		express_num = expressNum;
	}

	public int getExpress_money() {
		return express_money;
	}

	public void setExpress_money(int expressMoney) {
		express_money = expressMoney;
	}

	public String getExpress_person() {
		return express_person;
	}

	public void setExpress_person(String expressPerson) {
		express_person = expressPerson;
	}

	public String getExpress_phone() {
		return express_phone;
	}

	public void setExpress_phone(String expressPhone) {
		express_phone = expressPhone;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receiveTime) {
		receive_time = receiveTime;
	}

	public int getProduct_count() {
		return product_count;
	}

	public void setProduct_count(int productCount) {
		product_count = productCount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getIs_express() {
		return is_express;
	}

	public void setIs_express(int isExpress) {
		is_express = isExpress;
	}

	public int getReceipt_status() {
		return receipt_status;
	}

	public void setReceipt_status(int receiptStatus) {
		receipt_status = receiptStatus;
	}

	public String getProduct_nums() {
		return product_nums;
	}

	public void setProduct_nums(String productNums) {
		product_nums = productNums;
	}

	public int getSend_count() {
		return send_count;
	}

	public void setSend_count(int sendCount) {
		send_count = sendCount;
	}
}
