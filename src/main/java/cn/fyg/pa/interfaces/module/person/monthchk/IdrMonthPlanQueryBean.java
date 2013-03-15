package cn.fyg.pa.interfaces.module.person.monthchk;

import cn.fyg.pa.infrastructure.util.DateTool;

//XXX 被多出引用 应该重构
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
