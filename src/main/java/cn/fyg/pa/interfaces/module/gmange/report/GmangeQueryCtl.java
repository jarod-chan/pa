package cn.fyg.pa.interfaces.module.gmange.report;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.IdrMonthPlanBillService;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.interfaces.module.person.monthchk.IdrMonthPlanQueryBean;
import cn.fyg.pa.interfaces.module.shared.tool.DateTool;

@Controller
@RequestMapping("/gmange/{personId}/query")
public class GmangeQueryCtl {
	@Resource
	MonthChkRepository monthChkRepository;
	
	@Resource
	IdrMonthPlanBillService idrMonthPlanBillService;
	
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
		
	@Resource
	DepartmentRepository departmentRepository;

	@RequestMapping(value="/idrmonthplan",method=RequestMethod.GET)
	public String queryIdrMonthPlan(IdrMonthPlanQueryBean queryBean,Map<String,Object> map){
		List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillRepository.findIdrMonthPlanBillByPeriodAndState(queryBean.getYear(), queryBean.getMonth(), IdrMonthPlanEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("queryPage", queryBean);
		map.put("idrMonthPlanBills", idrMonthPlanBills);
		return "gmangequery/idrmonthplan";
	}
	

	@RequestMapping(value="/monthchk",method=RequestMethod.GET)
	public String queryMonthchk(MonthChkQueryBean queryBean,Map<String,Object> map){
		List<Department> departments=departmentRepository.findAllDepartmentsOrderById();
		List<MonthChk> monthChks=monthChkRepository.getMonthChkByPeriodAndDepartmentAndState(queryBean.getYear(), queryBean.getMonth(),queryBean.getDepartment(), MonthChkEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("departments",departments);
		map.put("queryBean", queryBean);
		map.put("monthChks", monthChks);
		return "gmangequery/monthchk";
	}

}
