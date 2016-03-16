package com.xiaorui.common.util;

import java.util.List;
/**
 * 处理分页的类
 * @author Administrator
 *
 */
public class PageModel {
	/**
	 *查询出来的记录总数
	 */
	private int count;
	/**
	 * 每页显示的数据
	 */
	private List<?> datas;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<?> getDatas() {
		return datas;
	}
	public void setDatas(List<?> datas) {
		this.datas = datas;
	}
	
}
