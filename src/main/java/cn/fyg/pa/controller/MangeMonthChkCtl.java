package cn.fyg.pa.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.dao.MonthChkDao;
import cn.fyg.pa.message.imp.SessionMPR;
import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.enums.MonthChkEnum;
import cn.fyg.pa.page.ManageMonthChkPage;
import cn.fyg.pa.service.MonthChkService;
import cn.fyg.pa.service.PersonService;

@Controller
@RequestMapping("/mange/{personId}/monthchk")
public class MangeMonthChkCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(MangeMonthChkCtl.class);

	@Resource
	PersonService personService;
	
	@Resource
	MonthChkService monthChkService;
	
	@Autowired
	MonthChkDao monthChkDao;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personService.find(personId);
	}
	
	
	@RequestMapping(value="")
	public String toList(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		logger.info("toList");
		List<MonthChk> monthChks=monthChkService.getMonthChkByDepartmentAndState(person.getDepartment(), MonthChkEnum.SUBMITTED);
		map.put("mange", person);
		map.put("monthChks", monthChks);
		map.put("message",new SessionMPR(session).getMessage());
		return "mangemonthchk/list";
	}
	
	@RequestMapping(value="/{monthchkId}")
	public String toEvaluate(@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,Map<String,Object> map,HttpSession session){
		logger.info("toEvaluate");
		MonthChk monthChk=monthChkService.find(monthchkId);
		map.put("mange", person);
		map.put("monthChk", monthChk);
		map.put("message",new SessionMPR(session).getMessage());
		return "mangemonthchk/evaluate";
	}
	
	@RequestMapping(value="/{monthchkId}/save",method=RequestMethod.POST)
	public String save(@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,HttpServletRequest request,HttpSession session){
		logger.info("save");
		MonthChk monthChk=monthChkService.find(monthchkId);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(monthChk);//XXX 能否修改这里的逻辑？
		binder.bind(request);	
		monthChkService.save(monthChk);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:/mange/"+person.getId()+"/monthchk/"+monthchkId;

	}

	@RequestMapping(value="/{monthchkId}/finish",method=RequestMethod.POST)
	public String finish(ManageMonthChkPage manageMonthChkPage,@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId, HttpServletRequest request,HttpSession session){
		logger.info("finish");
		MonthChk monthChk=monthChkService.find(monthchkId);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(monthChk);//XXX 能否修改这里的逻辑？
		binder.bind(request);	
		monthChkService.save(monthChk);
		new SessionMPR(session).setMessage("单据已完成！");
		//TODO   工作到此处暂停 ，此方法没有完成
		return "redirect:/mange/"+person.getId()+"/monthchk";
	}


	@RequestMapping(value="/histroy")
	public ModelAndView histroy(@ModelAttribute("person")Person person,HttpSession session){
		logger.info("histroy");
		
		List<MonthChk> monthChks=monthChkDao.getAllFinishMonthChkByDept(person.getDepartment());
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("mange", person);
		mav.addObject("monthChks", monthChks);
		mav.addObject("msg",new SessionMPR(session).getMessage());
		mav.setViewName("mangemonthchk/histroy");
		return mav;
	}
	
	@RequestMapping(value="/{monthchkId}/back",method=RequestMethod.POST)
	public ModelAndView back(ManageMonthChkPage manageMonthChkPage,@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,HttpSession session){
		
		MonthChk monthChk=monthChkDao.find(monthchkId);	
		manageMonthChkPage.initMonthChk(monthChk);
		monthChk.setState(MonthChkEnum.SAVED);
		monthChkDao.save(monthChk);
		new SessionMPR(session).setMessage("单据已打回！");
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/mange/"+person.getId()+"/monthchk");
		return mav;
	}
	
}
