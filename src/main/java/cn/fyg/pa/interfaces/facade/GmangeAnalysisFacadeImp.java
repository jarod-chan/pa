package cn.fyg.pa.interfaces.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.department.DepartmentRepository;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.person.ManageEnum;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.person.PersonRepository;
import cn.fyg.pa.interfaces.bean.AnalysisIdrMonthPlanBean;
import cn.fyg.pa.interfaces.bean.AnalysisIdrMonthPlanBuilder;
import cn.fyg.pa.interfaces.bean.AnalysisMonthChkBean;
import cn.fyg.pa.interfaces.bean.AnalysisMonthChkBuiler;

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
		List<Person> persons=personRepository.getPersonByManage(ManageEnum.N);
		List<MonthChk> monthChks=monthChkReposity.findMonthChkByPeriod(year, month);
		AnalysisMonthChkBuiler builder=new AnalysisMonthChkBuiler(persons, monthChks);
		return builder.build(year,month);
	}
	
}
