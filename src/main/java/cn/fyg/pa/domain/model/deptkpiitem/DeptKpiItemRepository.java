package cn.fyg.pa.domain.model.deptkpiitem;

import java.util.List;

import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;
import cn.fyg.pa.domain.model.department.Department;

public interface DeptKpiItemRepository {
	
	DeptKpiItem find(Long id);
	
	DeptKpiItem save(DeptKpiItem deptKpiItem);
	
	void remove(DeptKpiItem deptKpiItem);
	
	List<DeptKpiItem> findByYearAndDepartmentAndIdrCompanyOrderBySn(Long year,Department department,IdrCompany idrCompany);
}
