package com.xiaorui.admin.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.xiaorui.admin.service.TbUserService;
import com.xiaorui.common.util.PageModel;

public class AreaAction {
	
	@Resource(name="tbUserService")
	private TbUserService tbUserServers;
	
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	
	@SuppressWarnings("unchecked")
	public String areaInput(){
		List areas = tbUserServers.findArea(null,null);
		request.setAttribute("areas", areas);
		return "areas";
	}
	
	public String addAreaInput(){
		return "addArea";
	}
	
	public String addArea(){
		String areaName= request.getParameter("areaName");
		List list = tbUserServers.findArea(areaName, null);
		if(list.size()==0){
			tbUserServers.saveArea(areaName);
			request.setAttribute("message", "地区信息添加成功!");
		}else{
			request.setAttribute("message", "地区已经存在,添加失败!");
		}
		return areaInput();
	}
	
	@SuppressWarnings("unchecked")
	public String editAreaInput(){
		Integer areaId = Integer.parseInt(request.getParameter("areaId"));
		Map area = (Map) tbUserServers.findArea(null,areaId).get(0);
		request.setAttribute("area", area);
		return "area";
	}
	
	public String editArea(){
		Integer areaId = Integer.parseInt(request.getParameter("areaId"));
		String areaName = request.getParameter("areaName");
		tbUserServers.updateArea(areaName,areaId);
		request.setAttribute("message", "地区信息修改成功!");
		return areaInput();
	}
	
	// 进入客户管理
	public String customInput() {
		String area_name = request.getParameter("area");
		String name_xm = request.getParameter("name");
		PageModel date = tbUserServers.findAllCustom(area_name,name_xm);
		request.setAttribute("date", date);
		request.setAttribute("area", area_name);
		request.setAttribute("name", name_xm);
		return "customInput";
	}

	// 删除客户
	public String delCustom() {
		int mail_id = Integer.parseInt(request.getParameter("id"));
		tbUserServers.deleteCustom(mail_id);
		request.setAttribute("message", "客户已删除!");
		return customInput();
	}

	// 进入添加客户页面
	public String addCustomInput() {
		return "addCustomInput";
	}

	// 保存客户
	public String saveCustom() {
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String py = request.getParameter("py");
		String bz = request.getParameter("bz");

		tbUserServers.saveCustom(name, tel, py, bz);
		request.setAttribute("message", "客户已保存!");
		return customInput();
	}

	// 进入编辑页面
	public String editCustomInput() {
		int mail_id = Integer.parseInt(request.getParameter("id"));
		Map custom = tbUserServers.findCustomById(mail_id);
		request.setAttribute("custom", custom);
		return "editCustomInput";
	}

	// 修改客户信息
	public String updateCustom() {
		int mail_id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String area = request.getParameter("area");
		String tel = request.getParameter("tel");
		String py = request.getParameter("py");
		String bz = request.getParameter("bz");

		tbUserServers.updateCustom(mail_id, name, tel, area, py, bz);
		request.setAttribute("message", "客户已跟新!");
		return customInput();
	}
	
	//核实是否存在相同用户
	public String checkName() throws Exception{
		String name = request.getParameter("name");
		int count = tbUserServers.findCustomName(name);
		response.setContentType("text/plain");
		if(count > 0){
			response.getWriter().write("failure");
		}
		return null;
	}
}
