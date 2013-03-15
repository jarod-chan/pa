package cn.fyg.pa.interfaces.module.person.monthchk;

import cn.fyg.pa.infrastructure.util.DateTool;

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
