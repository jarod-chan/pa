package cn.fyg.pa.application;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicator;

public interface DeptIndicatorService {
	
	DeptIndicator getByYearAndDepartment(Long year,Department department);
	
	DeptIndicator saveDeptIndicator(DeptIndicator deptIndicator);

}
