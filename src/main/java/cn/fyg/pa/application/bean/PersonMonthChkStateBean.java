package cn.fyg.pa.application.bean;

import cn.fyg.pa.domain.model.enums.MonthChkEnum;

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
