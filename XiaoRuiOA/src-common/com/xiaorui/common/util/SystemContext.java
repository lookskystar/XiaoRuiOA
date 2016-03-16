package com.xiaorui.common.util;

public class SystemContext {

	private static ThreadLocal<Integer> offsetLocal=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageSizeLocal=new ThreadLocal<Integer>();
	
	public static void setOffset(Integer offset) {
		offsetLocal.set(offset);
	}
	
	public static Integer getOffset(){
		return offsetLocal.get();
	}
	
	public static void removeOffset(){
		if(offsetLocal.get()!=null)
			offsetLocal.remove();
	}
	
	public static void setPageSize(Integer pageSize){
		pageSizeLocal.set(pageSize);
	}
	
	public static Integer getPageSize(){
		return pageSizeLocal.get();
	}
	
	public static void removePageSize(){
		if(pageSizeLocal.get()!=null)
			pageSizeLocal.remove();
	}
}
