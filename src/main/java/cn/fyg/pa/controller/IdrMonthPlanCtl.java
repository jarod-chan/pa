package cn.fyg.pa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.dao.PersonDao;
import cn.fyg.pa.message.imp.SessionMPR;
import cn.fyg.pa.model.Department;
import cn.fyg.pa.model.IdrMonthPlanBill;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.StateChangeException;
import cn.fyg.pa.model.enums.IdrMonthPlanEnum;
import cn.fyg.pa.service.DepartmentService;
import cn.fyg.pa.service.IdrMonthPlanBillService;

@Controller
@RequestMapping("/mange/{personId}/idrmonthplan")
public class IdrMonthPlanCtl {
	
	public static Map<IdrMonthPlanEnum,String> PAGEMAP=new HashMap<IdrMonthPlanEnum,String>();
	
	static{
		PAGEMAP.put(IdrMonthPlanEnum.SAVED, "idrmonthplan/edit");
		PAGEMAP.put(IdrMonthPlanEnum.SUBMITTED, "idrmonthplan/view");
		PAGEMAP.put(IdrMonthPlanEnum.EXECUTE, "idrmonthplan/summary");
		PAGEMAP.put(IdrMonthPlanEnum.FINISHED, "idrmonthplan/view");
	}
	
	@Resource
	PersonDao personDao;
	
	@Resource
	IdrMonthPlanBillService idrMonthPlanBillService; 
	
	@Resource
	DepartmentService departmentService; 
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personDao.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toEdit(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		String departmentName=person.getDepartment();
		Department department=departmentService.findByName(departmentName);
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillService.getCurrentIdrMonthPlanBill(department);
		map.put("person", person);
		map.put("idrMonthPlanBill", idrMonthPlanBill);
		map.put("message", new SessionMPR(session).getMessage());
		return PAGEMAP.get(idrMonthPlanBill.getState());
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(IdrMonthPlanBill idrMonthPlanBill,HttpSession session){
		idrMonthPlanBill.setState(IdrMonthPlanEnum.SAVED);
		idrMonthPlanBill=idrMonthPlanBillService.save(idrMonthPlanBill);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:../idrmonthplan";
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(IdrMonthPlanBill idrMonthPlanBill,HttpSession session){
		idrMonthPlanBill.setState(IdrMonthPlanEnum.SAVED);
		idrMonthPlanBill=idrMonthPlanBillService.save(idrMonthPlanBill);
		String message="提交成功！";
		try {
			idrMonthPlanBillService.next(idrMonthPlanBill.getId());
		} catch (StateChangeException e) {
			message=String.format("提交失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:../idrmonthplan";
	}

}
