package cn.fyg.pa.interfaces.deptkpi;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.application.DeptKpiService;
import cn.fyg.pa.domain.companykpi.IdrCompany;
import cn.fyg.pa.domain.companykpi.IdrCompanyRepository;
import cn.fyg.pa.domain.companykpi.IdrYearCompany;
import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.department.DepartmentRepository;
import cn.fyg.pa.domain.deptkpi.DeptKpi;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.service.IdrYearCompanyService;

@Component
public class DeptKpiFacade {
	
	@Resource
	IdrYearCompanyService idrYearCompanyService;
	@Resource
	DeptKpiService deptKpiService;
	@Resource
	DepartmentRepository departmentRepository;
	@Resource 
	IdrCompanyRepository idrCompanyRepository;
	
	
	public ListPage getDeptKpiByYearAndDepartment(Long year, Long departmentId) {
		IdrYearCompany idrYearCompany = idrYearCompanyService.findByYear(year);
		List<IdrCompany> idrCompanys = idrYearCompany.getIdrCompany();
		Department department = departmentRepository.find(departmentId);
		DeptKpi deptKpi = deptKpiService.getDeptKpiByYearAndDepartment(year, department);
		List<DeptKpiItem> deptKpiItems = deptKpi.getDeptKpiItems();
		ListPageBuilder builder=new ListPageBuilder(idrCompanys,deptKpiItems);
		return builder.build(year,department);
	}
	


	public void saveDeptKpiItems(Long year, Long departmentId,Long idrcompanyId, List<DeptKpiItem> deptKpiItems) {
		Department department = departmentRepository.find(departmentId);
		DeptKpi deptKpi = deptKpiService.getDeptKpiByYearAndDepartment(year, department);
		IdrCompany idrCompany = idrCompanyRepository.find(idrcompanyId);
		for(DeptKpiItem deptKpiItem:deptKpiItems){
			deptKpiItem.setDeptKpi(deptKpi);
			deptKpiItem.setIdrCompany(idrCompany);
		}
		deptKpiService.saveDeptKpiItems(deptKpi,idrCompany,deptKpiItems);
	}

}
