package cn.fyg.pa.interfaces.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.service.DepartmentService;
import cn.fyg.pa.interfaces.bean.MonthChkQueryBean;
import cn.fyg.pa.interfaces.person.monthchk.IdrMonthPlanQueryBean;
import cn.fyg.pa.interfaces.tool.DateTool;

@Controller
@RequestMapping("/gmange/{personId}/query")
public class GmangeQueryCtl {
	
	
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
	
	@RequestMapping(value="/idrmonthplan",method=RequestMethod.GET)
	public String queryIdrMonthPlan(IdrMonthPlanQueryBean queryBean,Map<String,Object> map){
		List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillRepository.findIdrMonthPlanBillByPeriodAndState(queryBean.getYear(), queryBean.getMonth(), IdrMonthPlanEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("queryPage", queryBean);
		map.put("idrMonthPlanBills", idrMonthPlanBills);
		return "gmangequery/idrmonthplan";
	}
	
	
	@Resource
	MonthChkRepository monthChkRepository;
	@Resource
	DepartmentService departmentService;

	@RequestMapping(value="/monthchk",method=RequestMethod.GET)
	public String queryMonthchk(MonthChkQueryBean queryBean,Map<String,Object> map){
		List<Department> departments=departmentService.getAllDepartmentsOrderById();
		List<MonthChk> monthChks=monthChkRepository.getMonthChkByPeriodAndDepartmentAndState(queryBean.getYear(), queryBean.getMonth(),queryBean.getDepartment(), MonthChkEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("departments",departments);
		map.put("queryBean", queryBean);
		map.put("monthChks", monthChks);
		return "gmangequery/monthchk";
	}

}
