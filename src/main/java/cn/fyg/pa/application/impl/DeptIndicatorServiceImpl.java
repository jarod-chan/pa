package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.DeptIndicatorService;
import cn.fyg.pa.domain.model.companykpi.IdrYearCompany;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicator;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicatorFactory;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicatorRepository;
import cn.fyg.pa.domain.service.IdrYearCompanyService;

@Service
public class DeptIndicatorServiceImpl implements DeptIndicatorService {

	@Resource
	DeptIndicatorRepository deptIndicatorRepository;
	
	@Resource
	IdrYearCompanyService idrYearCompanyService;
	
	@Override
	public DeptIndicator getByYearAndDepartment(Long year, Department department) {
		DeptIndicator deptIndicator=deptIndicatorRepository.findByYearAndDepartment(year, department);
		if(deptIndicator==null){
			IdrYearCompany idrYearCompany = idrYearCompanyService.findByYear(year);
			deptIndicator = DeptIndicatorFactory.createDeptIndicator(department, idrYearCompany);
		}
		return deptIndicator;
	}

	@Override
	@Transactional
	public DeptIndicator saveDeptIndicator(DeptIndicator deptIndicator) {
		return deptIndicatorRepository.save(deptIndicator);
	}

}
