package cn.fyg.pa.interfaces.controller;


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

import cn.fyg.pa.domain.model.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.domain.model.StateChangeException;
import cn.fyg.pa.domain.model.enums.IdrMonthPlanEnum;
import cn.fyg.pa.domain.service.IdrMonthPlanBillService;
import cn.fyg.pa.infrastructure.message.imp.SessionMPR;
import cn.fyg.pa.infrastructure.perisistence.PersonDao;

@Controller
@RequestMapping("/gmange/{personId}/idrmonthplan")
public class GmangeIdrMonthPlanChkCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(GmangeIdrMonthPlanChkCtl.class);
	
	public static Map<IdrMonthPlanEnum,String> PAGEMAP=new HashMap<IdrMonthPlanEnum,String>();
	{
		PAGEMAP.put(IdrMonthPlanEnum.SUBMITTED, "gmangeidrmonthplan/audit");
		PAGEMAP.put(IdrMonthPlanEnum.EXECUTE, "gmangeidrmonthplan/view");
	}
	
	@Resource
	PersonDao personDao;
	
	@Resource
	IdrMonthPlanBillService idrMonthPlanBillService;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personDao.find(personId);
	}
	
	@RequestMapping(value="")
	public String index(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		logger.info("index");
		List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillService.getIdrMonthPlanBillByGmangeAndState(person,IdrMonthPlanEnum.SUBMITTED,IdrMonthPlanEnum.EXECUTE);
		map.put("person", person);
		map.put("idrMonthPlanBills", idrMonthPlanBills);
		map.put("message",new SessionMPR(session).getMessage());
		return "gmangeidrmonthplan/list";
	}
	
	@RequestMapping(value="/{idrmonthplanId}")
	public String toAudit(@PathVariable("idrmonthplanId")Long idrmonthplanId,@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillService.find(idrmonthplanId);
		Person mange=personDao.findDeptMange(idrMonthPlanBill.getDepartment().getName());
		map.put("mange", mange);
		map.put("idrMonthPlanBill", idrMonthPlanBill);
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
	
	@RequestMapping(value="/history",method=RequestMethod.GET)
	public String history(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillService.getIdrMonthPlanBillByGmangeAndState(person,IdrMonthPlanEnum.FINISHED);
		map.put("person", person);
		map.put("idrMonthPlanBills", idrMonthPlanBills);
		return "gmangeidrmonthplan/histroy";
	}
	

}
