package cn.fyg.pa.application.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.DeptIndicatorService;
import cn.fyg.pa.application.IdrYearCompanyService;
import cn.fyg.pa.domain.model.companykpi.IdrYearCompany;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicator;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicatorFactory;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicatorRepository;
import cn.fyg.pa.domain.model.deptindicator.IndicatorOption;

@Service
public class DeptIndicatorServiceImpl implements DeptIndicatorService {

	@Resource
	DeptIndicatorRepository deptIndicatorRepository;
	
	@Resource
	IdrYearCompanyService idrYearCompanyService;
	
	@Override
	public DeptIndicator getByYearAndDepartment(Long year, Department department) {
		IdrYearCompany idrYearCompany = idrYearCompanyService.findByYear(year);
		DeptIndicator deptIndicator = DeptIndicatorFactory.createDeptIndicator(department, idrYearCompany);
		
		DeptIndicator valIndicator=deptIndicatorRepository.findByYearAndDepartment(year, department);
		if(valIndicator!=null){
			Map<Long,Boolean> map=new HashMap<Long,Boolean>();
			for(IndicatorOption indicatorOption:valIndicator.getIndiactorOptions()){
				map.put(indicatorOption.getIdrCompany().getId(), indicatorOption.getMust());
			}
			deptIndicator.setId(valIndicator.getId());
			for(IndicatorOption indicatorOption:deptIndicator.getIndiactorOptions()){
				Boolean must=map.get(indicatorOption.getIdrCompany().getId());
				if(must==null) must=Boolean.FALSE;
				indicatorOption.setMust(must);
			}
		}
		return deptIndicator;
	}

	@Override
	@Transactional
	public DeptIndicator saveDeptIndicator(DeptIndicator deptIndicator) {
		return deptIndicatorRepository.save(deptIndicator);
	}

}
