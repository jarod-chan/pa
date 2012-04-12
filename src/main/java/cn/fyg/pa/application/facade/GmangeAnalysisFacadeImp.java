package cn.fyg.pa.application.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.application.bean.AnalysisIdrMonthPlanBean;
import cn.fyg.pa.application.bean.AnalysisIdrMonthPlanBuilder;
import cn.fyg.pa.application.bean.AnalysisMonthChkBean;
import cn.fyg.pa.application.bean.AnalysisMonthChkBuiler;
import cn.fyg.pa.domain.DepartmentRepository;
import cn.fyg.pa.domain.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.MonthChkRepository;
import cn.fyg.pa.domain.PersonRepository;
import cn.fyg.pa.domain.model.Department;
import cn.fyg.pa.domain.model.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.MonthChk;
import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.domain.model.enums.ManageEnum;

@Component
public class GmangeAnalysisFacadeImp implements GmangeAnalysisFacade {
	
	@Resource
	DepartmentRepository departmentRepository;
	
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
	

	@Override
	public AnalysisIdrMonthPlanBean analyseIdrMonthPlan(Long year, Long month){
		List<Department> departments=departmentRepository.getAllDepartmentsOrderById();
		List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillRepository.findByPeriod(year,month);
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
		List<MonthChk> monthChks=monthChkReposity.getEveryoneMonthChkByPeriod(year, month);
		AnalysisMonthChkBuiler builder=new AnalysisMonthChkBuiler(persons, monthChks);
		return builder.build(year,month);
	}
	
}
