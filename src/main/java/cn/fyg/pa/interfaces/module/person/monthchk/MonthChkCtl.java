package cn.fyg.pa.interfaces.module.person.monthchk;


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
import cn.fyg.pa.infrastructure.message.imp.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.tool.DateTool;

@Controller
@RequestMapping("/person/{personId}/monthchk")
public class MonthChkCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(MonthChkCtl.class);
	
	public static Map<MonthChkEnum,String> PAGEMAP=new HashMap<MonthChkEnum,String>();
	
	static{
		PAGEMAP.put(MonthChkEnum.NEW, "monthchk/edit");
		PAGEMAP.put(MonthChkEnum.SAVED, "monthchk/edit");
		PAGEMAP.put(MonthChkEnum.SUBMITTED, "monthchk/view");
		PAGEMAP.put(MonthChkEnum.FINISHED, "monthchk/view");
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
	


	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personRepository.find(personId);
	}
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toEdit(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		logger.info("toEdit");
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
		logger.info("save");
		monthChk.setState(MonthChkEnum.SAVED);
		monthChkService.save(monthChk);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:../monthchk"; 
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(MonthChk monthChk,HttpSession session){
		logger.info("commit");
		monthChk.setState(MonthChkEnum.SAVED);
		monthChk=monthChkService.save(monthChk);
		String message="提交成功！";
		try {
			monthChkService.next(monthChk.getId());
		} catch (StateChangeException e) {
			message=String.format("提交失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:../monthchk";
	}
	
	@RequestMapping(value="/histroy",method=RequestMethod.GET)
	public String histroy(MonthChkYearQueryBean queryBean,@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		logger.info("histroy");
		List<MonthChk> monthChks=monthChkRepository.getMonthChkByPersonAndState(queryBean.getYear(), person, MonthChkEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("queryBean", queryBean);
		map.put("monthChks", monthChks);
		map.put("person", person);
		map.put("message",new SessionMPR(session).getMessage());
		return "monthchk/histroy";
	}
	
	@RequestMapping(value="/idrmonthplan",method=RequestMethod.GET)
	public String idrMonthPlan(IdrMonthPlanQueryBean queryBean,@ModelAttribute("person")Person person,Map<String,Object> map){
		logger.info("idrMonthPlan");
		Department department = departmentRepository.findDepartmentByName(person.getDepartment());
		List<IdrMonthPlanBill> idrMonthPlanBills = idrMonthPlanBillRepository.findIdrMonthPlanBillByPeriodAndDepartmentAndState(
						queryBean.getYear(), 
						queryBean.getMonth(), 
						department,
						IdrMonthPlanEnum.EXECUTE, IdrMonthPlanEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("queryBean", queryBean);
		map.put("idrMonthPlanBills", idrMonthPlanBills);
		return "monthchk/idrmonthplan";
	}
	
}
