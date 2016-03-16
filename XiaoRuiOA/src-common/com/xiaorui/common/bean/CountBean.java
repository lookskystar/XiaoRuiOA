package com.xiaorui.common.bean;

import java.util.List;
import java.util.Map;

/**
 * 统计显示的bean类
 * @author dell
 *
 */
public class CountBean {
	//地区
	private String area;
	//数量
	private Integer num=0;
	//维修记录信息
	private List<Map<String,Object>> repairRecs;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public List<Map<String, Object>> getRepairRecs() {
		return repairRecs;
	}
	public void setRepairRecs(List<Map<String, Object>> repairRecs) {
		this.repairRecs = repairRecs;
	}
}
