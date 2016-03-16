package com.xiaorui.admin.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.xiaorui.admin.service.TbUserService;

/**
 * 用户权限读取
 * @author lll
 *
 */
public class FunctionAction {
	@Resource(name="tbUserService")
	private TbUserService tbUserService;
	
	private HttpServletRequest request=ServletActionContext.getRequest();
	
	@SuppressWarnings("unchecked")
	public String getUserFuncs() throws Exception {
		Map user = (Map) request.getSession().getAttribute("session_user");
		Integer roleid = (Integer) user.get("user_role_id");
		Map<String,List> funMap= tbUserService.getFunctionsByUser(roleid);
		
		request.setAttribute("funMap", funMap);
		return "left";
	}
}
