package cn.fyg.pa.interfaces.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.MonthChkService;
import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.person.PersonRepository;
import cn.fyg.pa.domain.shared.state.StateChangeException;
import cn.fyg.pa.infrastructure.message.imp.SessionMPR;
import cn.fyg.pa.interfaces.bean.ManageMonthChkQueryBean;
import cn.fyg.pa.interfaces.tool.DateTool;

@Controller
@RequestMapping("/mange/{personId}/monthchk")
public class MangeMonthChkCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(MangeMonthChkCtl.class);

	@Resource
	PersonRepository personRepository;
	@Resource
	MonthChkService monthChkService;
	@Resource
	MonthChkRepository monthChkRepository;
	
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toList(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		logger.info("toList");
		List<MonthChk> monthChks=monthChkRepository.getMonthChkByDepartmentAndState(person.getDepartment(), MonthChkEnum.SUBMITTED);
		map.put("mange", person);
		map.put("monthChks", monthChks);
		map.put("message",new SessionMPR(session).getMessage());
		return "mangemonthchk/list";
	}
	
	@RequestMapping(value="/{monthchkId}",method=RequestMethod.GET)
	public String toEvaluate(@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,Map<String,Object> map,HttpSession session){
		logger.info("toEvaluate");
		MonthChk monthChk=monthChkRepository.find(monthchkId);
		map.put("mange", person);
		map.put("monthChk", monthChk);
		map.put("message",new SessionMPR(session).getMessage());
		return "mangemonthchk/evaluate";
	}
	
	@RequestMapping(value="/{monthchkId}/save",method=RequestMethod.POST)
	public String save(@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,HttpServletRequest request,HttpSession session){
		logger.info("save");
		MonthChk monthChk=monthChkRepository.find(monthchkId);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(monthChk);//XXX 能否修改这里的逻辑？
		binder.bind(request);	
		monthChkService.save(monthChk);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:../"+monthchkId;
	}

	@RequestMapping(value="/{monthchkId}/finish",method=RequestMethod.POST)
	public String finish(@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId, HttpServletRequest request,HttpSession session){
		logger.info("finish");
		MonthChk monthChk=monthChkRepository.find(monthchkId);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(monthChk);//XXX 能否修改这里的逻辑？
		binder.bind(request);	
		monthChkService.save(monthChk);
		String message="单据已完成！";
		try {
			monthChkService.next(monthChk.getId());
		} catch (StateChangeException e) {
			message=String.format("完成失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:../../monthchk";
	}


	@RequestMapping(value="/{monthchkId}/back",method=RequestMethod.POST)
	public String back(@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,HttpSession session){
		String message="单据已打回！";
		try {
			monthChkService.back(monthchkId);
		} catch (StateChangeException e) {
			message=String.format("打回失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:../../monthchk";
	}
	


	@RequestMapping(value="/histroy",method=RequestMethod.GET)
	public String histroy(ManageMonthChkQueryBean queryBean,@ModelAttribute("person")Person person,Map<String,Object> map){
		logger.info("histroy");
		List<MonthChk> monthChks=monthChkRepository.getMonthChkByPeriodAndDepartmentAndState(queryBean.getYear(), queryBean.getMonth(),person.getDepartment(), MonthChkEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("mange", person);
		map.put("monthChks", monthChks);
		map.put("queryBean", queryBean);
		return "mangemonthchk/histroy";
	}
	
}
