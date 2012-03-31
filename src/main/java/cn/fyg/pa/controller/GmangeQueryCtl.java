package cn.fyg.pa.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.bean.IdrMonthPlanQueryPage;
import cn.fyg.pa.bean.MonthchkQueryPage;
import cn.fyg.pa.model.Department;
import cn.fyg.pa.model.IdrMonthPlanBill;
import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.enums.IdrMonthPlanEnum;
import cn.fyg.pa.model.enums.StateEnum;
import cn.fyg.pa.service.DepartmentService;
import cn.fyg.pa.service.IdrMonthPlanBillService;
import cn.fyg.pa.service.MonthChkService;
import cn.fyg.pa.tool.DateTool;

@Controller
@RequestMapping("/gmange/{personId}/query")
public class GmangeQueryCtl {
	@Resource
	MonthChkService monthChkService;
	
	@Resource
	IdrMonthPlanBillService idrMonthPlanBillService;
	
	@Resource
	DepartmentService departmentService;

	@RequestMapping(value="/idrmonthplan",method=RequestMethod.GET)
	public String queryIdrMonthPlan(IdrMonthPlanQueryPage page,Map<String,Object> map){
		page=intiIdrMonthPlanQuery(page);
		List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillService.getIdrMonthPlanBillByPeriodAndState(page.getYear(), page.getMonth(), IdrMonthPlanEnum.FINISHED);
		DateTool dtl=new DateTool();
		map.put("years", dtl.getAllYears());
		map.put("months", dtl.getAllMonths());
		map.put("queryPage", page);
		map.put("idrMonthPlanBills", idrMonthPlanBills);
		return "gmangequery/idrmonthplan";
	}
	
	
	private IdrMonthPlanQueryPage intiIdrMonthPlanQuery(IdrMonthPlanQueryPage page) {
		DateTool dtl=new DateTool();
		if(page==null){
			page=new IdrMonthPlanQueryPage();
			page.setYear(dtl.getCurrentYear());
			page.setMonth(dtl.getCurrentMonth());
			return page;
		}
		if(page.getYear()==null){
			page.setYear(dtl.getCurrentYear());
		}
		if(page.getMonth()==null){
			page.setMonth(dtl.getCurrentMonth());
		}
		return page;
	}

	@RequestMapping(value="/monthchk",method=RequestMethod.GET)
	public String queryMonthchk(MonthchkQueryPage page,Map<String,Object> map){
		page=initMonthchkQueryPage(page,"办公室");
		List<Department> departments=departmentService.getAllDepartmentsOrderById();
		List<MonthChk> monthChks=monthChkService.getMonthChkByPeriodAndState(page.getYear(), page.getMonth(),page.getDepartment(), StateEnum.FINISHED);
		DateTool dtl=new DateTool();
		map.put("years", dtl.getAllYears());
		map.put("months", dtl.getAllMonths());
		map.put("departments",departments);
		map.put("queryPage", page);
		map.put("monthChks", monthChks);
		return "gmangequery/monthchk";
	}


	private MonthchkQueryPage initMonthchkQueryPage(MonthchkQueryPage page,String department) {
		DateTool dtl=new DateTool();
		if(page==null){
			page=new MonthchkQueryPage();
			page.setYear(dtl.getCurrentYear());
			page.setMonth(dtl.getCurrentMonth());
			page.setDepartment(department);
			return page;
		}
		if(page.getYear()==null){
			page.setYear(dtl.getCurrentYear());
		}
		if(page.getMonth()==null){
			page.setMonth(dtl.getCurrentMonth());
		}
		if(page.getDepartment()==null){
			page.setDepartment(department);
		}
		return page;
	}
}
