package cn.fyg.pa.interfaces.manage.monthplan;

import cn.fyg.pa.interfaces.tool.DateTool;

public class YearQueryBean {

	private Long year;
	
	public YearQueryBean(){
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
