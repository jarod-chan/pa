package cn.fyg.pa.controller;


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
import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.enums.StateEnum;
import cn.fyg.pa.page.MonthChkPage;
import cn.fyg.pa.tool.NumToChinese;

@Controller
@RequestMapping("/person/{personId}/monthchk")
public class MonthChkCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(MonthChkCtl.class);
	
	@Autowired
	PersonDao personDao;
	
	@Autowired
	MonthChkDao monthChkDao;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId ){
		logger.info("initPerson");
		return personDao.find(personId);
	}

	
	@RequestMapping(value="")
	public ModelAndView monthchk(@ModelAttribute("person")Person person ){
		logger.info("monthchk");
		
		MonthChk monthChk=monthChkDao.getCurrMonthChk(person);

		ModelAndView mav=new ModelAndView();
		mav.addObject("monthChk", monthChk);
		mav.addObject("currMonth", NumToChinese.monthChinese(monthChk.getMonth()));
		mav.setViewName("monthchk/monthchk");
		return mav;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(MonthChkPage monthChkPage,@ModelAttribute("person")Person person){
		logger.info("save");
		
		MonthChk monthChk=monthChkDao.getMonthChk(person, monthChkPage.getYear(), monthChkPage.getMonth());
		monthChkPage.updateMonthChk(monthChk);
		
		monthChk=monthChkDao.save(monthChk);
		monthChk.setState(StateEnum.SAVED);

		ModelAndView mav=new ModelAndView();
		mav.addObject("monthChk", monthChk);
		mav.addObject("currMonth", NumToChinese.monthChinese(monthChk.getMonth()));
		mav.setViewName("monthchk/monthchk");
		return mav;
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public ModelAndView commit(MonthChkPage monthChkPage,@ModelAttribute("person")Person person){
		logger.info("commit");
		
		MonthChk monthChk=monthChkDao.getMonthChk(person, monthChkPage.getYear(), monthChkPage.getMonth());
		monthChkPage.updateMonthChk(monthChk);
		
		monthChk=monthChkDao.save(monthChk);
		monthChk.setState(StateEnum.SUBMITTED);

		ModelAndView mav=new ModelAndView();
		mav.addObject("monthChk", monthChk);
		mav.addObject("currMonth", NumToChinese.monthChinese(monthChk.getMonth()));
		mav.setViewName("monthchk/monthchk");
		return mav;
	}
}
