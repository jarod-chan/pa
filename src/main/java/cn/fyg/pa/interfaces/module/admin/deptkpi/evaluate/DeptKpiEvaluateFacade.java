package cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.application.DeptKpiService;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptkpi.DeptKpi;
import cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.list.PageBean;

@Component
public class DeptKpiEvaluateFacade {
	
	@Resource
	DeptKpiService deptKpiService;
	@Resource
	DepartmentRepository departmentRepository;

	public PageBean getDeptKpiForEvaluateList(Long year, Long departmentId) {
		Department department = departmentRepository.find(departmentId);
		DeptKpi deptKpi=deptKpiService.getDeptKpiByYearAndDepartment(year, department);
		ListBuilder builder=new ListBuilder(deptKpi);
		return builder.build();
	}

}
