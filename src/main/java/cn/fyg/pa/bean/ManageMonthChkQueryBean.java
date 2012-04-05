package cn.fyg.pa.bean;

import cn.fyg.pa.tool.DateTool;

public class ManageMonthChkQueryBean {
	private Long year;
	private Long month;
	
	

	public ManageMonthChkQueryBean() {
		DateTool dtl=new DateTool();
		this.year=dtl.getCurrentYear();
		this.month=dtl.getCurrentMonth();
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
