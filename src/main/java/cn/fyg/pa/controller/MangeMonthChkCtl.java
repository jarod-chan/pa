package cn.fyg.pa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.dao.MonthChkDao;
import cn.fyg.pa.dao.PersonDao;
import cn.fyg.pa.message.imp.SessionMPR;
import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.enums.StateEnum;
import cn.fyg.pa.page.ManageMonthChkPage;

@Controller
@RequestMapping("/mange/{personId}/monthchk")
public class MangeMonthChkCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(MangeMonthChkCtl.class);

	@Autowired
	PersonDao personDao;
	
	@Autowired
	MonthChkDao monthChkDao;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personDao.find(personId);
	}
	
	
	@RequestMapping(value="")
	public ModelAndView showMonthchkList(@ModelAttribute("person")Person person,HttpSession session){
		logger.info("showMonthchkList");
		
		List<MonthChk> monthChks=monthChkDao.getAllCommitMonthChkByDept(person.getDepartment(),StateEnum.SUBMITTED);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mangemonthchk/list");
		mav.addObject("mange", person);
		mav.addObject("monthChks", monthChks);
		mav.addObject("msg",new SessionMPR(session).getMessage());
		return mav;
	}
	
	@RequestMapping(value="/{monthchkId}")
	public ModelAndView showEvaluate(@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,HttpSession session){
		logger.info("showEvaluate");
		
		MonthChk monthChk=monthChkDao.find(monthchkId);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mangemonthchk/evaluate");
		mav.addObject("mange", person);
		mav.addObject("monthChk", monthChk);
		mav.addObject("msg",new SessionMPR(session).getMessage());
		return mav;
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
	
	@RequestMapping(value="/{monthchkId}/save",method=RequestMethod.POST)
	public ModelAndView save(ManageMonthChkPage manageMonthChkPage,@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,HttpSession session){
		
		MonthChk monthChk=monthChkDao.find(monthchkId);	
		manageMonthChkPage.updateMonthChk(monthChk);
		
		monthChk.setState(StateEnum.SUBMITTED);
		monthChkDao.save(monthChk);
		new SessionMPR(session).setMessage("保存成功！");
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/mange/"+person.getId()+"/monthchk/"+monthchkId);
		return mav;
	}
	
	@RequestMapping(value="/{monthchkId}/finish",method=RequestMethod.POST)
	public ModelAndView finish(ManageMonthChkPage manageMonthChkPage,@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,HttpSession session){
		
		MonthChk monthChk=monthChkDao.find(monthchkId);	
		manageMonthChkPage.updateMonthChk(monthChk);
		
		monthChk.setState(StateEnum.FINISHED);
		monthChkDao.save(monthChk);
		new SessionMPR(session).setMessage("单据已完成！");
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/mange/"+person.getId()+"/monthchk");
		return mav;
	}
	
	@RequestMapping(value="/{monthchkId}/back",method=RequestMethod.POST)
	public ModelAndView back(ManageMonthChkPage manageMonthChkPage,@ModelAttribute("person")Person person,@PathVariable("monthchkId") Long monthchkId,HttpSession session){
		
		MonthChk monthChk=monthChkDao.find(monthchkId);	
		manageMonthChkPage.initMonthChk(monthChk);
		monthChk.setState(StateEnum.SAVED);
		monthChkDao.save(monthChk);
		new SessionMPR(session).setMessage("单据已打回！");
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/mange/"+person.getId()+"/monthchk");
		return mav;
	}
	
}
