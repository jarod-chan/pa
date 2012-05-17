package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.DeptKpiService;
import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptkpi.DeptKpi;
import cn.fyg.pa.domain.deptkpi.DeptKpiFactory;
import cn.fyg.pa.domain.deptkpi.DeptKpiRepository;

@Service
public class DeptKpiServiceImpl implements DeptKpiService {
	
	@Resource
	DeptKpiRepository deptKpiRepository;

	@Override
	public DeptKpi getDeptKpiByYearAndDepartment(Long year, Department department) {
		DeptKpi deptKpi = deptKpiRepository.findByYearAndDepartment(year, department);
		if(deptKpi==null){
			return DeptKpiFactory.createDeptKpiByYearAndDepartment(year, department);
		}
		return deptKpi;
	}

}
