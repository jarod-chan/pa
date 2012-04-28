package cn.fyg.pa.interfaces.yearchk;

import java.util.List;

public class DepartmentChkBean {
	
	private String department;
	
	private List<PersonChkBean> personChkBeans;
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<PersonChkBean> getPersonChkBeans() {
		return personChkBeans;
	}

	public void setPersonChkBeans(List<PersonChkBean> personChkBeans) {
		this.personChkBeans = personChkBeans;
	}

	
}
