package cn.fyg.pa.interfaces.module.admin.deptkpi.preview;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.application.DeptKpiService;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptkpi.DeptKpi;
import cn.fyg.pa.interfaces.module.admin.deptkpi.preview.dto.PreviewPage;

@Component
public class PreviewFacade {
	
	@Resource
	DepartmentRepository departmentRepository;
	@Resource
	DeptKpiService deptKpiService;
	
	public PreviewPage getDeptKpiForPreview(Long year,Long departmentId){
		Department department = departmentRepository.find(departmentId);
		DeptKpi deptKpi = deptKpiService.getDeptKpiByYearAndDepartment(year, department);
		PreviewBuilder builder=new PreviewBuilder(deptKpi);
		PreviewPage previewPage = builder.build();
		return previewPage;
	}

}
