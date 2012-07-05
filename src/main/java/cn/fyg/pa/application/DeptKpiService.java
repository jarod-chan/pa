package cn.fyg.pa.application;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptkpi.DeptKpi;
import cn.fyg.pa.domain.shared.Result;

public interface DeptKpiService {
	
	DeptKpi getDeptKpiByYearAndDepartment(Long year,Department department);
	
	DeptKpi save(DeptKpi deptKpi);
	
	Result commitDeptKpi(DeptKpi deptKpi);

}
