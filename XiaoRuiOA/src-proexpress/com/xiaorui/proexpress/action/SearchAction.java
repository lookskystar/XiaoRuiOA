package com.xiaorui.proexpress.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

import com.xiaorui.proexpress.service.ProExpressService;

public class SearchAction {
	@Resource(name = "proExpressService")
	private ProExpressService proExpressService;
	
	private String keyQuery;// 前台传过来的文本框中的值
	private String fast;// 条件参数
	private String jsonResult;// 用于接收json数据，传到前台页面可以直接用result中的item接收

	@SuppressWarnings("unchecked")
	public String search() throws Exception {
		// 这里用list模拟数据，当然在实际项目中我们则是从数据库中取数据然后转换
		List<String> list = proExpressService.findMailName(keyQuery);

		this.jsonResult = parseResNameResult(list);// 将数据转换为相应的json格式数据
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		response.getWriter().print(jsonResult);// 传到前台页面，ajax请求
		return null;
	}

	// 定义转换函数
	private String parseResNameResult(List list) throws JSONException {
		Map map = null;
		StringBuffer buffer = new StringBuffer("[");
		for (int i = 0; i < list.size(); i++) {
			String str =  list.get(i).toString();
			str = str.substring(11, str.length()-1);
			buffer.append("\"" + str + "\",");
		}
		if (buffer.length() > 2) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
		buffer.append("]");
		return buffer.toString();
	}
	
	// 数据格式为["abc","sawe","wewe","中国","人们"]
	public String getKeyQuery() {
		return keyQuery;
	}

	public void setKeyQuery(String keyQuery) {
		this.keyQuery = keyQuery;
	}

	public String getFast() {
		return fast;
	}

	public void setFast(String fast) {
		this.fast = fast;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
}