package cn.fyg.pa.domain.deptkpi;

import cn.fyg.pa.domain.department.Department;

public class DeptKpiFactory {
	
	public static DeptKpi createDeptKpiByYearAndDepartment(Long year,Department department){
		DeptKpi deptKpi=new DeptKpi();
		deptKpi.setYear(year);
		deptKpi.setDepartment(department);
		return deptKpi;
	}

}
