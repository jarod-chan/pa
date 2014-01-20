package cn.fyg.pa.interfaces.module.gmange.report.analyseidrmonthplan;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.interfaces.module.gmange.report.analyseidrmonthplan.dto.AnalysisIdrMonthPlanBean;

@Component
public class AnalysisIdrMonthPlanFacade {
	
	@Resource
	DepartmentRepository departmentRepository;
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
	

	public AnalysisIdrMonthPlanBean analysisIdrMonthPlan(Long year, Long month){
		List<Department> departments=departmentRepository.findAllDepartmentsOrderById();
		List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillRepository.findIdrMonthPlanBillByPeriod(year,month);
		AnalysisIdrMonthPlanBuilder builder = new AnalysisIdrMonthPlanBuilder(departments,idrMonthPlanBills);
		return builder.build(year, month);
	}
	

	
}
