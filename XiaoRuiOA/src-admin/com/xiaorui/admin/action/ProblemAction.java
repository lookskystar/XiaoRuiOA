package com.xiaorui.admin.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.xiaorui.admin.service.TbUserService;
import com.xiaorui.common.util.PageModel;

public class ProblemAction {
	@Resource(name="tbUserService")
	private TbUserService tbUserService;
	
	private HttpServletRequest request=ServletActionContext.getRequest();
	
	/**
	 * 进入故障页面
	 * @return
	 */
	public String problemInput(){
		String problemName = request.getParameter("problemName");
		PageModel pm = tbUserService.findAllProblem(problemName);
		request.setAttribute("pm", pm);
		return "problem";
	}
	
	/**
	 * 进入新增故障页面
	 * @return
	 * @throws Exception
	 */
	public String addProblemInput() throws Exception{
		return "addproblem";
	}
	
	/**
	 * 新增故障
	 * @return
	 */
	public String addProblem(){
		tbUserService.saveProblem(request.getParameter("problem_name"));
		request.setAttribute("message", "故障信息添加成功!");
		return problemInput();
	}

	/**
	 * 进入编辑故障页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String editProblemInput(){
		Integer problemId = Integer.parseInt(request.getParameter("problemId"));
		Map map = tbUserService.findProblemById(problemId);
		request.setAttribute("problem", map);
		return "editeproblem";
	}
	
	/**
	 * 编辑故障
	 * @return
	 */
	public String editeProblem(){
		Integer problemId = Integer.parseInt(request.getParameter("problemId"));
		String problemName = request.getParameter("problem_name");
		tbUserService.updateProblem(problemName, problemId);
		request.setAttribute("message", "故障内容修改成功!");
		return problemInput();
	}
	
	/**
	 * 删除故障
	 * @return
	 */
	public String deleteProblemById(){
		tbUserService.deleteProblemById(Integer.parseInt(request.getParameter("problemId")));
		return problemInput();
	}
}
