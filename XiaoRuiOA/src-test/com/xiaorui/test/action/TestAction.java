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
//		//�õ�һ��Map������ҳ����ͨ��map.(���ݿ����ֶ�����)�õ���Ӧ������
//		request.setAttribute("map", map);
//		//�õ�һ��List������ҳ���ϱ���list����,ͨ��entry.(���ݿ����ֶ�����)�õ���Ӧ������
//		request.setAttribute("list", list);
		System.out.println("���Գɹ�!");
		return SUCCESS;
	}

}
