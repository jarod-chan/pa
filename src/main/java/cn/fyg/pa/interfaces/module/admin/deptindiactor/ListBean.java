package cn.fyg.pa.interfaces.module.admin.deptindiactor;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptindicator.DeptIndicator;

public class ListBean {
	
	private Department department;
	
	private DeptIndicator deptIndicator;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public DeptIndicator getDeptIndicator() {
		return deptIndicator;
	}

	public void setDeptIndicator(DeptIndicator deptIndicator) {
		this.deptIndicator = deptIndicator;
	}


}
