package cn.fyg.pa.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.fyg.pa.model.enums.IdrMonthPlanEnum;

@Entity
public class IdrMonthPlanBill extends IdrTaskBill {
	
	private Long year;//年度
	
	private Long month;//月度
	
	@Enumerated(EnumType.STRING)
	private IdrMonthPlanEnum state;//单据状体
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;//部门

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

	public IdrMonthPlanEnum getState() {
		return state;
	}

	public void setState(IdrMonthPlanEnum state) {
		this.state = state;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void next() throws StateChangeException {
		state.setIdrMonthPlanBill(this);
		this.state.next();
	}
	
	public void back() throws StateChangeException{
		state.setIdrMonthPlanBill(this);
		this.state.back();
	}
	
}
