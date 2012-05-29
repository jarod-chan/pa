package cn.fyg.pa.domain.model.deptkpi;

import cn.fyg.pa.domain.model.department.Department;

public interface DeptKpiRepository {
	
	DeptKpi find(Long id);
	
	DeptKpi findByYearAndDepartment(Long year,Department department);
	
	DeptKpi save(DeptKpi deptKpi);

}
