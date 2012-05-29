package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.model.companykpi.IdrCompany;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptkpi.DeptKpi;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.shared.Result;

public interface DeptKpiService {
	
	DeptKpi getDeptKpiByYearAndDepartment(Long year,Department department);
	
	void saveDeptKpiItems(DeptKpi deptKpi, IdrCompany idrCompany,List<DeptKpiItem> deptKpiItems);
	
	Result commitDeptKpi(DeptKpi deptKpi);

}
