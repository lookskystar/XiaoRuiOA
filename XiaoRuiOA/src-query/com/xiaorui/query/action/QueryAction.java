package com.xiaorui.query.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.JFreeChart;

import com.xiaorui.common.bean.CountBean;
import com.xiaorui.common.util.PageModel;
import com.xiaorui.query.service.QueryService;
import com.xiaorui.query.util.JfreeChartUtil;

public class QueryAction {
	
	@Resource(name="queryService")
	private QueryService queryService;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private JFreeChart chart;
	//定义查询条件
	private static final String PERSON="PERSON";
	private static final String PROBLEM="PROBLEM";
	
	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	/**
	 * 查找收货记录信息
	 * @return
	 */
	public String queryReceiptRec(){
		PageModel pm=queryService.findReceiptRec();
		request.setAttribute("pm", pm);
		return "receipt_rec";
	}
	
	public String show() throws Exception {
		String type=request.getParameter("type");
		String time=request.getParameter("time");
		Map<String,Double> map=null;
		if(type!=null&&type.equals("area")){
			map=queryService.findRepairRecByArea(time);
			this.chart = JfreeChartUtil.createPieChart("各地区维修产品数量统计", map);//饼状图
		}else if(type!=null&&type.equals("person")){
			List<Map<String,Object>> list=queryService.findRepairRec(PERSON,time);
			map=new HashMap<String,Double>();
			if(list!=null&&list.size()>0){
				for(Map<String,Object> repairRec:list){
					String person=repairRec.get("repair_person")+"";
					Double count=Double.parseDouble(repairRec.get("count")+"");
					map.put(person, count);
				}
			}
			this.chart = JfreeChartUtil.createBarChart("各人员维修产品数量统计", "维修人员", "产品数量", map);//柱状图
		}
		//this.chart = JfreeChartUtil.createLineChart("折线图", "X轴标签", "Y轴标签");
		return "show";
	}
	
	
	/**
	 * 统计产品故障原因
	 * @return
	 */
	public String countRepairRecByProblem(){
		String time=request.getParameter("time");
		List<Map<String,Object>> list=queryService.findRepairRec(PROBLEM,time);
		request.setAttribute("list",list);
		request.setAttribute("time",time);
		return "problem_repair";
	}
	
	/**
	 * 售后汇总统计
	 * @return
	 */
	public String count(){
		String time=request.getParameter("time");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		if(time==null||"".equals(time)){
			time=format.format(new Date());
		}
		List<CountBean> list=queryService.findCount(time);
		request.setAttribute("list",list);
		request.setAttribute("time",time);
		return "count";
	}
}
