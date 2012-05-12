package cn.fyg.pa.domain.deptindicator;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.model.IdrCompany;
import cn.fyg.pa.domain.model.IdrYearCompany;

public class DeptIndicatorFactory {
	
	public static DeptIndicator createDeptIndicator(Department department,IdrYearCompany idrYearCompany){
		DeptIndicator deptIndicator=new DeptIndicator();
		deptIndicator.setYear(idrYearCompany.getYear());
		deptIndicator.setDepartment(department);
		
		List<IndicatorOption> indiactorOptions=new ArrayList<IndicatorOption>();
		for(IdrCompany idrCompany:idrYearCompany.getIdrCompany()){
			IndicatorOption indiactorOption = createIndiactorOption(idrCompany);
			indiactorOptions.add(indiactorOption);
		}
		deptIndicator.setIndiactorOptions(indiactorOptions);
		return deptIndicator;
	}

	private static IndicatorOption createIndiactorOption(IdrCompany idrCompany) {
		IndicatorOption indicatorOption=new IndicatorOption();
		indicatorOption.setSn(idrCompany.getSn());
		indicatorOption.setMust(Boolean.FALSE);
		indicatorOption.setIdrCompany(idrCompany);
		return indicatorOption;
	}

}
