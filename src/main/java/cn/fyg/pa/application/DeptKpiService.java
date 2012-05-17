package cn.fyg.pa.application;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptkpi.DeptKpi;

public interface DeptKpiService {
	
	DeptKpi getDeptKpiByYearAndDepartment(Long year,Department department);

}
