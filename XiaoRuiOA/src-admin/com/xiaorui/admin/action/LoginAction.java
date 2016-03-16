package com.xiaorui.admin.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xiaorui.admin.service.TbUserService;
import com.xiaorui.admin.service.UsersService;


/**
 * 用户登陆Action
 * @author lll
 * @param <tb_user>
 *
 */
public class LoginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6133933551689340553L;

	@Resource(name="tbUserService")
	private TbUserService tbUserService;
	@Resource(name="usersService")
	private UsersService usersService;
	
	private HttpServletRequest request=ServletActionContext.getRequest();

	/**
	 * 用户登陆
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String login(){
		String username = request.getParameter("loginName");
		String password = request.getParameter("loginPwd");
		
			Map user = tbUserService.login(username, password);
			if(user == null){
				request.setAttribute("loginError", "你输入的用户名或密码错误，请重新输入");
				return "loginOut";
			}
				request.getSession().setAttribute("session_user", user);
			
		return "main";
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	public String loginOut(){
		request.getSession().removeAttribute("session_user");
		request.getSession().setAttribute("session_user", null);
		return "loginOut";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updatePwd(){
		HttpServletResponse response=ServletActionContext.getResponse();
		String user_pwd=request.getParameter("new_pwd");
		Map user =  (Map) request.getSession().getAttribute("session_user");
		String result="failure";
		if(user==null){
			result="session_null";
		}else{
			Integer user_id = (Integer) user.get("user_id");
			Integer role_id = (Integer) user.get("user_role_id");
			Integer depe_id = (Integer) user.get("user_dept_id");
			String user_xm = (String) user.get("user_xm");
			String user_phone = (String) user.get("user_phone");
			String user_name = (String) user.get("user_name");
			
			usersService.updateTbUser(user_id,role_id,depe_id,user_xm,user_phone,user_name,user_pwd);
			result="success";
		}
		response.setContentType("text/plain");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取服务器时间
	 * @throws Exception 
	 */
	public String getServerTime() throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/hh:mm:ss");
		String timeStr = dateFormat.format(new Date());
		ServletActionContext.getResponse().getWriter().print(timeStr);
		return null;
	}
}
