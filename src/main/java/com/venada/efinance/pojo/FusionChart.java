package com.venada.efinance.pojo;

import java.util.List;

/**
 * fusionChart报表显示，辅助类
 * @author yma
 *
 */
public class FusionChart {
	
	public String caption ;
	
	public String xAxisName ;
	
	public String yAxisName ;
	
	public String numberPrefix ;
	
	public List<FusionChartData> data ;
	
	public FusionChart(){
		
	}
	
	public FusionChart(String caption,String xAsisName,String yAxisName){
		this.caption = caption ;
		this.xAxisName = xAsisName ;
		this.yAxisName = yAxisName ;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getxAxisName() {
		return xAxisName;
	}

	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}

	public String getyAxisName() {
		return yAxisName;
	}

	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}

	public String getNumberPrefix() {
		return numberPrefix;
	}

	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}

	public List<FusionChartData> getData() {
		return data;
	}

	public void setData(List<FusionChartData> data) {
		this.data = data;
	}

}
