package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.companykpi.IdrCompany;
import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptkpi.DeptKpi;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.model.Result;

public interface DeptKpiService {
	
	DeptKpi getDeptKpiByYearAndDepartment(Long year,Department department);
	
	void saveDeptKpiItems(DeptKpi deptKpi, IdrCompany idrCompany,List<DeptKpiItem> deptKpiItems);
	
	Result commitDeptKpi(DeptKpi deptKpi);

}
