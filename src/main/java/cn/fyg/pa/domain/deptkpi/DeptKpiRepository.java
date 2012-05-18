package cn.fyg.pa.domain.deptkpi;

import cn.fyg.pa.domain.department.Department;

public interface DeptKpiRepository {
	
	DeptKpi find(Long id);
	
	DeptKpi findByYearAndDepartment(Long year,Department department);
	
	DeptKpi save(DeptKpi deptKpi);

}
