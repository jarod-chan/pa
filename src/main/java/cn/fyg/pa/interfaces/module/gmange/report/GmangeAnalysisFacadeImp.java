package cn.fyg.pa.interfaces.module.gmange.report;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.ManageEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;

@Component
public class GmangeAnalysisFacadeImp implements GmangeAnalysisFacade {
	
	@Resource
	DepartmentRepository departmentRepository;
	
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
	

	@Override
	public AnalysisIdrMonthPlanBean analyseIdrMonthPlan(Long year, Long month){
		List<Department> departments=departmentRepository.findAllDepartmentsOrderById();
		List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillRepository.findIdrMonthPlanBillByPeriod(year,month);
		AnalysisIdrMonthPlanBuilder builder = new AnalysisIdrMonthPlanBuilder(departments,idrMonthPlanBills);
		return builder.build(year, month);
	}
	
	
	@Resource
	MonthChkRepository monthChkReposity;
	
	@Resource
	PersonRepository personRepository;
	
	@Override
	public AnalysisMonthChkBean analyseMonthChk(Long year,Long month){
		List<Person> persons=personRepository.findPersonByManage(ManageEnum.N);
		List<MonthChk> monthChks=monthChkReposity.findMonthChkByPeriod(year, month);
		AnalysisMonthChkBuiler builder=new AnalysisMonthChkBuiler(persons, monthChks);
		return builder.build(year,month);
	}
	
}
