package com.xiaorui.common.bean;

public class BackRec {

	//主键
	private int back_id;
	//回访时间
	private String back_time;
	//被回访人单位
	private int back_area;
	//被回访人
	private String back_person;
	//被回访人联系方式
	private int back_phone;
	//回访情况
	private String back_situation;
	//收货表
	private int receipt_id;
	//回访人（我公司人）
	private String do_person;
	
	public int getBack_id() {
		return back_id;
	}
	public void setBack_id(int backId) {
		back_id = backId;
	}
	public String getBack_time() {
		return back_time;
	}
	public void setBack_time(String backTime) {
		back_time = backTime;
	}
	public int getBack_area() {
		return back_area;
	}
	public void setBack_area(int backArea) {
		back_area = backArea;
	}
	public int getBack_phone() {
		return back_phone;
	}
	public void setBack_phone(int backPhone) {
		back_phone = backPhone;
	}
	public String getBack_situation() {
		return back_situation;
	}
	public void setBack_situation(String backSituation) {
		back_situation = backSituation;
	}
	public int getReceipt_id() {
		return receipt_id;
	}
	public void setReceipt_id(int receiptId) {
		receipt_id = receiptId;
	}
	public String getDo_person() {
		return do_person;
	}
	public void setDo_person(String doPerson) {
		do_person = doPerson;
	}
	public String getBack_person() {
		return back_person;
	}
	public void setBack_person(String backPerson) {
		back_person = backPerson;
	}
}
