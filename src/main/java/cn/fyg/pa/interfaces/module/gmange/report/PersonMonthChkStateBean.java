package cn.fyg.pa.interfaces.module.gmange.report;

import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;

public class PersonMonthChkStateBean {
	
	private String personName;
	
	private MonthChkEnum state;

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public MonthChkEnum getState() {
		return state;
	}

	public void setState(MonthChkEnum state) {
		this.state = state;
	}

}
