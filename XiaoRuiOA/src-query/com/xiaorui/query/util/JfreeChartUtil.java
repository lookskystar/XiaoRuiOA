package com.xiaorui.query.util;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;

/**
 * 显示相应的图表(饼图、折线图、柱状图)
 * 调用下面方法可以输出到相应的FileOutputStream
 * ChartUtilities.writeChartAsJPEG(out, quality, chart, width, height, info);
 * Map<String,Double> 键值：相应的描述  值：相应的值:这里不考虑相应描述下的多个值
 */
public class JfreeChartUtil {
	
	/**
	 * 创建饼状图
	 * @param title 标题
	 * @param map 键值对
	 * @return
	 */
	public static JFreeChart createPieChart(String title,Map<String,Double> map){
		DefaultPieDataset dpd=createPieDataset(map);
		// 创建PieChart对象(标题，数据集，是否显示图例，是否生成工具提示，是否生成URL链接)
		JFreeChart chart = ChartFactory.createPieChart3D(title, dpd,
				true, true, false);
		setPie3DStyle(chart);
		return chart;
	}
	
	  /**
     * 创建柱状图
     * @param title 标题
     * @param XLabel X轴标签
     * @param YLabel Y轴标签
     * @param map 键值对
     * @return
     */
	public static JFreeChart createBarChart(String title,String XLabel,String YLabel,Map<String,Double> map){
		DefaultCategoryDataset dataset=createBarDataSet(map);
		//创建一个柱状图(图表标题,X轴显示标签,Y轴显示标签,数据集,图表方向(水平or垂直),是否显示图例[对于简单图应为false],是否生成工具,是否生成url链接)
		JFreeChart chart = ChartFactory.createBarChart3D(title,XLabel, YLabel, dataset, PlotOrientation.VERTICAL, false, false, false);
		setBarStyle(chart);
		return chart;
	}
	
	/**
	 * 创建折线图
	 * @param title
	 * @param XLabel
	 * @param YLabel
	 * @return
	 */
	public static JFreeChart createLineChart(String title,String XLabel,String YLabel) {
		//该数据的Demo由系统提供，故不传入相应的Map或List数据了
		DefaultCategoryDataset dataset=createLineDataset();
		// 定义图表对象(折线图名称,横坐标名称,纵坐标名称,数据, 水平显示图像)
		JFreeChart chart = ChartFactory.createLineChart(title,XLabel,YLabel,dataset,PlotOrientation.VERTICAL,true,true,false);
		setLineStyle(chart);
		return chart;
	}
	
	/**
	 * 创建饼状图数据
	 * @param map
	 * @return
	 */
	private static DefaultPieDataset createPieDataset(Map<String,Double> map){
		DefaultPieDataset dpd = new DefaultPieDataset();
		Set<String> set=map.keySet();
		for(Iterator<String> itor=set.iterator();itor.hasNext();){
			String key=itor.next();
			dpd.setValue(key, map.get(key));
		}
		return dpd;
	}
	
	/**
	 * 设置饼状图样式
	 * @param chart
	 */
	@SuppressWarnings("unused")
	private static void setPieStyle(JFreeChart chart){
		PiePlot plot = (PiePlot) chart.getPlot();
		Font font = new Font("宋体", Font.ITALIC, 12);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 22));
		//方块距离饼的距离 只要负值就能把数据放到饼里 
		plot.setLabelFont(font);
		//去掉label
		//plot.setLabelGenerator(null);
		//在label上显示百分比
		//plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));
		//如果百分比要显示一位小数
		//plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}",new DecimalFormat("0.0"),new DecimalFormat("0.0%")));
		//在label上显示实际数值
		//plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}")); 
		//是否显示线 fasle就不显示了 
		//plot.setLabelLinkMargin(-0.8); 
		//plot.setLabelLinksVisible(false); 
		//有时我们想突出某一个来显示，需要加入下面一句：第一个参数是key,第二个参数是突出显示的大小（可以自己调整一下看看效果就明白了）  
		//plot.setExplodePercent("城管强拆",0.23);
		//设置相应的数据显示效果
		//StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1},{2})", new DecimalFormat("0.0"), new DecimalFormat("0.0"));  
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1},{2})", new DecimalFormat("0.0"), new DecimalFormat("0.0%"));
		plot.setLabelGenerator(standarPieIG); 
		//没有数据的时候所显示的内容
		plot.setNoDataMessage("没有相应的数据显示");
		chart.getLegend().setItemFont(font);
	}
	
	/**
	 * 设置饼状图3D样式
	 * @param chart
	 */
	private static void setPie3DStyle(JFreeChart chart){
		//获得3D的水晶饼图对象 
		PiePlot3D pieplot3d = (PiePlot3D) chart.getPlot(); 
		//设置开始角度 
		pieplot3d.setStartAngle(150D); 
		//设置方向为”顺时针方向“ 
		pieplot3d.setDirection(Rotation.CLOCKWISE); 
		//设置透明度，0.5F为半透明，1为不透明，0为全透明 
		pieplot3d.setForegroundAlpha(0.5F); 
		pieplot3d.setNoDataMessage("没有相应的数据显示"); 
		Font font = new Font("宋体", Font.ITALIC, 12);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 22));
		pieplot3d.setLabelFont(font);
		chart.getLegend().setItemFont(font);
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1},{2})", new DecimalFormat("0.0"), new DecimalFormat("0.0%"));
		pieplot3d.setLabelGenerator(standarPieIG); 
	}
	
	/**
	 * 创建柱状图数据
	 * @param list
	 * @return
	 */
	private static DefaultCategoryDataset createBarDataSet(Map<String,Double> map){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Set<String> set=map.keySet();
		for(Iterator<String> itor=set.iterator();itor.hasNext();){
			String key=itor.next();
			dataset.addValue(map.get(key), "", key);
		}
		return dataset;
	}
	
	/**
	 * 创建柱状图数据的第二种方式
	 * @return
	 */
	@SuppressWarnings("unused")
	private static CategoryDataset createBarDataSet2(){
		double[][] data = new double[][] {{1310, 1220, 1110, 1000},{720, 700, 680, 640},{1130, 1020, 980, 800},{440, 400, 360, 300}};
		String[] rowKeys = { "pig", "beef", "chicken", "fish" };
		String[] columnKeys = { "Guangzhou", "Shenzhen", "Dongguan", "Foshan" };
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		return dataset;
	}
	
	
	/**
	 * 设置柱状图样式
	 * @param chart
	 */
	private static void setBarStyle(JFreeChart chart){
		CategoryPlot plot=chart.getCategoryPlot();
		//设置标题字体样式
		chart.getTitle().setFont(new Font("黑体", Font.ITALIC,22));
		//取得横轴和设置横轴样式
		CategoryAxis categoryAxis = plot.getDomainAxis();
		categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
		//横轴分类标签
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 22));
		//取得纵轴和设置纵轴样式
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
		
		//显示每个柱的数值，并修改该数值的字体属性   
		BarRenderer3D renderer = new BarRenderer3D();    
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
        renderer.setBaseItemLabelsVisible(true);  
        //默认的数字显示在柱子中，通过如下两句可调整数字的显示   
        //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 ，将数字显示在柱状图上面
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(  
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));  
        renderer.setItemLabelAnchorOffset(10D);  
         //设置每个地区所包含的平行柱的之间距离   
         //renderer.setItemMargin(0.3);   
        plot.setRenderer(renderer);  
	}
	
	/**
	 * 生成折线图数据
	 * @return
	 */
	private static DefaultCategoryDataset createLineDataset() {
	    DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
	    // 各曲线名称
	    String series1 = "冰箱";
	    String series2 = "彩电";
	    String series3 = "洗衣机";
	    // 横轴名称(列名称)
	    String type1 = "1月";
	    String type2 = "2月";
	    String type3 = "3月";
	    linedataset.addValue(0.0, series1, type1);
	    linedataset.addValue(4.2, series1, type2);
	    linedataset.addValue(3.9, series1, type3);
	    linedataset.addValue(1.0, series2, type1);
	    linedataset.addValue(5.2, series2, type2);
	    linedataset.addValue(7.9, series2, type3);
	    linedataset.addValue(2.0, series3, type1);
	    linedataset.addValue(9.2, series3, type2);
	    linedataset.addValue(8.9, series3, type3);
	    return linedataset;
	}
	
	/**
	 * 设置折线图样式
	 * 
	 * @param chart
	 */
	private static void setLineStyle(JFreeChart chart){
		CategoryPlot plot = chart.getCategoryPlot();
		//设置标题字体样式
		chart.getTitle().setFont(new Font("黑体", Font.ITALIC,22));
		//取得横轴和设置横轴样式
		CategoryAxis categoryAxis = plot.getDomainAxis();
		categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
		//横轴分类标签
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 22));
		plot.setRangeGridlinesVisible(true); //是否显示格子线
		plot.setBackgroundAlpha(0.3f); //设置背景透明度
		NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
		//设置工具工具提示字体样式
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 22));
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		rangeAxis.setUpperMargin(0.20);
		rangeAxis.setLabelAngle(Math.PI / 2.0);
	}
}
