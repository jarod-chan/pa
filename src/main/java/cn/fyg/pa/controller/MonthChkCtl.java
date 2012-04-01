package cn.fyg.pa.controller;


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

import cn.fyg.pa.message.imp.SessionMPR;
import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.StateChangeException;
import cn.fyg.pa.model.WorkType;
import cn.fyg.pa.model.enums.MonthChkEnum;
import cn.fyg.pa.service.MonthChkService;
import cn.fyg.pa.service.PersonService;
import cn.fyg.pa.service.WorkTypeService;

@Controller
@RequestMapping("/person/{personId}/monthchk")
public class MonthChkCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(MonthChkCtl.class);
	
	public static Map<MonthChkEnum,String> PAGEMAP=new HashMap<MonthChkEnum,String>();
	
	static{
		PAGEMAP.put(MonthChkEnum.SAVED, "monthchk/edit");
		PAGEMAP.put(MonthChkEnum.SUBMITTED, "monthchk/view");
		PAGEMAP.put(MonthChkEnum.FINISHED, "monthchk/view");
	}
	
	@Resource
	PersonService personService;
	
	@Resource
	MonthChkService monthChkService;
	
	@Resource
	WorkTypeService workTypeService;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personService.find(personId);
	}
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toEdit(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		logger.info("toEdit");
		MonthChk monthChk=monthChkService.getCurrentMonthChk(person);
		Person mange=personService.getDeptMange(person.getDepartment());
		List<WorkType> workTypes=workTypeService.getAllWorkType();
		
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
	public String histroy(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		logger.info("histroy");
		List<MonthChk> monthChks=monthChkService.getAllFinishMonthChkByPerson(person);
		map.put("monthChks", monthChks);
		map.put("person", person);
		map.put("msg",new SessionMPR(session).getMessage());
		return "monthchk/histroy";
	}
	
}
