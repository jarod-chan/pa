package cn.fyg.pa.application;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptindicator.DeptIndicator;

public interface DeptIndicatorService {
	
	DeptIndicator getByYearAndDepartment(Long year,Department department);
	
	DeptIndicator saveDeptIndicator(DeptIndicator deptIndicator);

}
