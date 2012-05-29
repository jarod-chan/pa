package cn.fyg.pa.interfaces.module.gmange.report;

import cn.fyg.pa.interfaces.module.shared.tool.DateTool;

public class YearAndMonthQueryBean {
	
	private Long year;
	
	private Long month;
	
	public YearAndMonthQueryBean(){
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
