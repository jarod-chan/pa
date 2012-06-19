package cn.fyg.pa.interfaces.module.gmange.report.analysismonthchk.dto;

import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;

public class PersonMonthChkStateBean {
	
	private String personName;
	
	private MonthChkEnum state;
	
	private Long monthchkId;

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

	public Long getMonthchkId() {
		return monthchkId;
	}

	public void setMonthchkId(Long monthchkId) {
		this.monthchkId = monthchkId;
	}

}
