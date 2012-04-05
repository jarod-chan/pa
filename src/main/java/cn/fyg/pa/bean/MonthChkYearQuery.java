package cn.fyg.pa.bean;

import cn.fyg.pa.tool.DateTool;

public class MonthChkYearQuery {

	private Long year;
	
	public MonthChkYearQuery(){
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
