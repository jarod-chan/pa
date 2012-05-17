package cn.fyg.pa.domain.deptkpi;

import cn.fyg.pa.domain.department.Department;

public interface DeptKpiRepository {
	
	DeptKpi findByYearAndDepartment(Long year,Department department);

}
