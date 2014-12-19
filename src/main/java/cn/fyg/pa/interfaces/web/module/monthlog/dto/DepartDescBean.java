package cn.fyg.pa.interfaces.web.module.monthlog.dto;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.person.Person;

public class DepartDescBean {

	private Department departemnt;
	
	private Person manager;
	
	private String findPersonListKey;
	
	
	public String getFindPersonListKey() {
		return findPersonListKey;
	}

	public void setFindPersonListKey(String findPersonListKey) {
		this.findPersonListKey = findPersonListKey;
	}

	public Department getDepartemnt() {
		return departemnt;
	}

	public void setDepartemnt(Department departemnt) {
		this.departemnt = departemnt;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

}
