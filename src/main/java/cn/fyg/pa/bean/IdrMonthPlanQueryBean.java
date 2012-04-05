package cn.fyg.pa.bean;

import cn.fyg.pa.tool.DateTool;

public class IdrMonthPlanQueryBean {
	
	private Long year;
	private Long month;
	
	public IdrMonthPlanQueryBean(){
		DateTool dateTool=new DateTool();
		this.year=dateTool.getCurrentYear();
		this.month=dateTool.getCurrentMonth();
	}
	
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Long getMonth() {
		return month;
	}
	public void setMonth(Long month) {
		this.month = month;
	}


}
