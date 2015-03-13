package cn.fyg.pa.interfaces.web.module.monthplan.gmange;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.fyg.pa.application.IdrMonthPlanBillService;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.shared.state.StateChangeException;
import cn.fyg.pa.interfaces.web.advice.personin.annotation.PersonIn;
import cn.fyg.pa.interfaces.web.shared.bean.YearAndPrevMonth;
import cn.fyg.pa.interfaces.web.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.web.shared.tool.DateTool;

@Controller
@RequestMapping("monthplan/gm")
@SessionAttributes("monthplan_gm_history") 
public class GmangeIdrMonthPlanChkCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(GmangeIdrMonthPlanChkCtl.class);
	
	
	private static final String PATH="gmangeidrmonthplan/";

	private interface Page {
		String AUDIT = PATH + "audit";
		String VIEW = PATH + "view";
		String HISTROY = PATH + "histroy";
	}
	
	public static Map<IdrMonthPlanEnum,String> PAGEMAP=new HashMap<IdrMonthPlanEnum,String>();
	static {
		PAGEMAP.put(IdrMonthPlanEnum.SUBMITTED, Page.AUDIT);
		PAGEMAP.put(IdrMonthPlanEnum.EXECUTE,  Page.VIEW);
	}
	
	@Resource
	PersonRepository personRepository;
	@Resource
	DepartmentRepository departmentRepository;
	@Resource
	IdrMonthPlanBillService idrMonthPlanBillService;
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
	
	@RequestMapping(value="")
	@PersonIn(0)
	public String toList(Person person,Map<String,Object> map,HttpSession session){
		List<Department> departments = departmentRepository.findDepartmentsByGmanage(person);
		if(departments!=null&&!departments.isEmpty()){			
			List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillRepository.findIdrMonthPlanBillByDepartmentAndState(departments,IdrMonthPlanEnum.SUBMITTED,IdrMonthPlanEnum.EXECUTE);
			map.put("idrMonthPlanBills", idrMonthPlanBills);
		}
		map.put("person", person);
		map.put("message",new SessionMPR(session).getMessage());
		return "gmangeidrmonthplan/list";
	}
	
	@RequestMapping(value="/{idrmonthplanId}")
	@PersonIn(0)
	public String toAudit(Person person,@PathVariable("idrmonthplanId")Long idrmonthplanId,Map<String,Object> map,HttpSession session){
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillRepository.find(idrmonthplanId);
		Person mange=personRepository.findDepartmentMange(idrMonthPlanBill.getDepartment().getName());
		map.put("mange", mange);
		map.put("idrMonthPlanBill", idrMonthPlanBill);
		map.put("person", person);
		return PAGEMAP.get(idrMonthPlanBill.getState());
	}
	
	@RequestMapping(value="/{idrmonthplanId}/next")
	public String next(@PathVariable("idrmonthplanId")Long idrmonthplanId,Map<String,Object> map,HttpSession session){
		String message="工作计划审核成功！";
		try {
			idrMonthPlanBillService.next(idrmonthplanId);
		} catch (StateChangeException e) {
			logger.error("", e);
			message=String.format("工作计划审核失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:../";
	}
	
	@RequestMapping(value="/{idrmonthplanId}/back")
	public String back(@PathVariable("idrmonthplanId")Long idrmonthplanId,Map<String,Object> map,HttpSession session){
		String message="工作计划已打回！";
		try {
			idrMonthPlanBillService.back(idrmonthplanId);
		} catch (StateChangeException e) {
			logger.error("", e);
			message=String.format("工作计划打回失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:../";
	}
	
	
	@ModelAttribute("monthplan_gm_history")
	public YearAndPrevMonth monthplan_gm_history(){
		return new YearAndPrevMonth();
	}
	
	@RequestMapping(value="/history",method={RequestMethod.GET,RequestMethod.POST})
	@PersonIn(1)
	public String history(@ModelAttribute("monthplan_gm_history")YearAndPrevMonth monthplan_gm_history,Person person,Map<String,Object> map){
		List<Department> departments = departmentRepository.findDepartmentsByGmanage(person);
		if(departments!=null&&!departments.isEmpty()){			
			List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillRepository.findIdrMonthPlanBillByPeriodAndDepartmentAndState(monthplan_gm_history.getYear(),monthplan_gm_history.getMonth(),departments,IdrMonthPlanEnum.FINISHED);
			map.put("idrMonthPlanBills", idrMonthPlanBills);
		}

		map.put("person", person);
		map.put("dateTool", new DateTool());
		return	Page.HISTROY;
	}
	

}
