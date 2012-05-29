package cn.fyg.pa.interfaces.module.admin.deptindiactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.application.DeptIndicatorService;
import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.department.DepartmentRepository;
import cn.fyg.pa.domain.deptindicator.DeptIndicator;
import cn.fyg.pa.domain.deptindicator.DeptIndicatorRepository;

@Component
public class DeptindiactorFacade {
	
	@Resource
	DepartmentRepository departmentRepository;
	@Resource
	DeptIndicatorRepository deptIndicatorRepository;
	@Resource
	DeptIndicatorService deptIndicatorService;
	
	public List<ListBean> getListBeans(Long year){
		List<Department> departments=departmentRepository.findAllDepartmentsOrderById();
		List<DeptIndicator> deptIndicators=deptIndicatorRepository.findByYear(year);
		Map<Long,DeptIndicator> deptIndicatorsMap=createDeptIndicatorsMap(deptIndicators);
		List<ListBean> listBeans=createLsitBeans(departments,deptIndicatorsMap);
		return listBeans;
	}

	private List<ListBean> createLsitBeans(List<Department> departments,Map<Long, DeptIndicator> deptIndicatorsMap) {
		List<ListBean> listBeans = new ArrayList<ListBean>();
		for (Department department : departments) {
			ListBean listBean=new ListBean();
			listBean.setDepartment(department);
			listBean.setDeptIndicator(deptIndicatorsMap.get(department.getId()));
			listBeans.add(listBean);
			
		}
		return listBeans;
	}

	private Map<Long, DeptIndicator> createDeptIndicatorsMap(
			List<DeptIndicator> deptIndicators) {
		Map<Long,DeptIndicator> map = new HashMap<Long,DeptIndicator>();
		for (DeptIndicator deptIndicator : deptIndicators) {
			map.put(deptIndicator.getDepartment().getId(), deptIndicator);
		}
		return map;
	}
	
	public DeptIndicator getByYearAndDepartment(Long year,Long departmentId){
		Department department=departmentRepository.find(departmentId);
		return deptIndicatorService.getByYearAndDepartment(year, department);
	}
	
	public DeptIndicator saveDeptIndicator(DeptIndicator deptIndicator){
		return deptIndicatorService.saveDeptIndicator(deptIndicator);
	}
}
