package cn.fyg.pa.domain.deptkpiitem;

import java.util.List;

import cn.fyg.pa.domain.department.Department;

public interface DeptKpiItemRepository {
	
	DeptKpiItem find(Long id);
	
	DeptKpiItem save(DeptKpiItem deptKpiItem);
	
	List<DeptKpiItem> findByYearAndDepartmentOrderBySn(Long year,Department department);
}
