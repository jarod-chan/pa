package cn.fyg.pa.application.bean;

import cn.fyg.pa.tool.DateTool;

public class MonthChkYearQueryBean {

	private Long year;
	
	public MonthChkYearQueryBean(){
		DateTool dateTool=new DateTool();
		this.year=dateTool.getCurrentYear();
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}
	
	
}
