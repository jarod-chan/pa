package cn.fyg.pa.interfaces.web.module.monthsmy;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.fyg.pa.application.MonthChkService;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.worktype.WorkType;
import cn.fyg.pa.domain.model.worktype.WorkTypeRepository;
import cn.fyg.pa.domain.shared.state.StateChangeException;
import cn.fyg.pa.interfaces.web.advice.personin.annotation.PersonIn;
import cn.fyg.pa.interfaces.web.shared.bean.YearBean;
import cn.fyg.pa.interfaces.web.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.web.shared.tool.DateTool;

@Controller
@RequestMapping("/monthsmy")
@SessionAttributes("monthsmy_histroy")
public class MonthsmyCtl {
	
	private static final String PATH="monthchk/";	
	private interface Page {
		String EDIT = PATH + "edit";
		String VIEW = PATH + "view";
		String HISTROY = PATH + "histroy";
		String IDRMONTHPLAN = PATH + "idrmonthplan";
	}
	
	public static Map<MonthChkEnum,String> PAGEMAP=new HashMap<MonthChkEnum,String>();
	static{
		PAGEMAP.put(MonthChkEnum.NEW, Page.EDIT);
		PAGEMAP.put(MonthChkEnum.SAVED, Page.EDIT);
		PAGEMAP.put(MonthChkEnum.SUBMITTED, Page.VIEW);
		PAGEMAP.put(MonthChkEnum.FINISHED, Page.VIEW);
	}
	
	@Resource
	PersonRepository personRepository;
	@Resource
	WorkTypeRepository workTypeRepository;	
	@Resource
	MonthChkService monthChkService;
	@Resource
	MonthChkRepository monthChkRepository;
	@Resource
	DepartmentRepository  departmentRepository;
	@Resource
	IdrMonthPlanBillRepository  idrMonthPlanBillRepository;
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	@PersonIn(0)
	public String toEdit(Person person,Map<String,Object> map,HttpSession session){
		MonthChk monthChk=monthChkService.getCurrentMonthChk(person);
		Person mange=personRepository.findDepartmentMange(person.getDepartment());
		List<WorkType> workTypes=workTypeRepository.findAllWorkTypes();
		
		map.put("mange", mange);
		map.put("monthChk", monthChk);
		map.put("workTypes", workTypes);
		map.put("message",new SessionMPR(session).getMessage());
		return PAGEMAP.get(monthChk.getState());
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(MonthChk monthChk,HttpSession session){
		monthChk.setState(MonthChkEnum.SAVED);
		monthChkService.save(monthChk);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:/monthsmy"; 
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(MonthChk monthChk,HttpSession session){
		monthChk.setState(MonthChkEnum.SAVED);
		monthChk=monthChkService.save(monthChk);
		String message="提交成功！";
		try {
			monthChkService.next(monthChk.getId());
		} catch (StateChangeException e) {
			message=String.format("提交失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:/monthsmy";
	}
	
	@ModelAttribute("monthsmy_histroy")
	public YearBean monthsmy_histroy(){
		return new YearBean();
	}
	
	@RequestMapping(value="histroy",method={RequestMethod.GET,RequestMethod.POST})
	@PersonIn(1)
	public String histroy(@ModelAttribute("monthsmy_histroy")YearBean monthsmy_histroy,Person person,Map<String,Object> map,HttpSession session){
		List<MonthChk> monthChks=monthChkRepository.getMonthChkByPersonAndState(monthsmy_histroy.getYear(), person, MonthChkEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("monthChks", monthChks);
		map.put("person", person);
		map.put("message",new SessionMPR(session).getMessage());
		return Page.HISTROY;
	}
	
	@RequestMapping(value="/idrmonthplan",method=RequestMethod.GET)
	@PersonIn(1)
	public String idrMonthPlan(IdrMonthPlanQueryBean queryBean,Person person,Map<String,Object> map){
		Department department = departmentRepository.findDepartmentByName(person.getDepartment());
		List<IdrMonthPlanBill> idrMonthPlanBills = idrMonthPlanBillRepository.findIdrMonthPlanBillByPeriodAndDepartmentAndState(
						queryBean.getYear(), 
						queryBean.getMonth(), 
						department,
						IdrMonthPlanEnum.EXECUTE, IdrMonthPlanEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("queryBean", queryBean);
		map.put("idrMonthPlanBills", idrMonthPlanBills);
		return Page.IDRMONTHPLAN;
	}
	
}
