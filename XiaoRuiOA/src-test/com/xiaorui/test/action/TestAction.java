package com.xiaorui.test.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xiaorui.test.service.TestService;

public class TestAction  extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7677271713141656868L;
	@Resource(name="testService")
	private TestService testService;
	private HttpServletRequest request=ServletActionContext.getRequest();
	
	public String execute(){
//		Long id=Long.parseLong(request.getParameter("id"));
//		String name=request.getParameter("name");
//		Map map=testService.getUserById(id);
//		List list=testService.getUserByName(name);
//		//得到一个Map对象，在页面上通过map.(数据库中字段名称)得到相应的数据
//		request.setAttribute("map", map);
//		//得到一个List对象，在页面上遍历list集合,通过entry.(数据库中字段名称)得到相应的数据
//		request.setAttribute("list", list);
		System.out.println("测试成功!");
		return SUCCESS;
	}

}
