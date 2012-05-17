package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.DeptIndicatorService;
import cn.fyg.pa.domain.companykpi.IdrYearCompany;
import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptindicator.DeptIndicator;
import cn.fyg.pa.domain.deptindicator.DeptIndicatorFactory;
import cn.fyg.pa.domain.deptindicator.DeptIndicatorRepository;
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
