package cn.fyg.pa.interfaces.module.gmange.report;

import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanEnum;

public class DepartmentIdrMonthPlanBillStateBean {
	
	private String departmentName;
	
	private IdrMonthPlanEnum state;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public IdrMonthPlanEnum getState() {
		return state;
	}

	public void setState(IdrMonthPlanEnum state) {
		this.state = state;
	}

}
