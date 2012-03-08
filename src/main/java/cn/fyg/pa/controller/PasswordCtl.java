package cn.fyg.pa.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.message.MessagePasser;
import cn.fyg.pa.message.imp.SessionMPR;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.page.LoginRet;
import cn.fyg.pa.page.PasswordPage;
import cn.fyg.pa.service.PersonService;
import cn.fyg.pa.tool.SessionUtil;

@Controller
@RequestMapping("/common/settings/person/{personId}/password")
public class PasswordCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(PasswordCtl.class);
	
	@Resource
	PersonService personService;
	
	@ModelAttribute("person")
	public Person initPerson(@ModelAttribute("loginRet") LoginRet loginRet,@PathVariable("personId") Long personId){
		logger.info("initPerson");
		//return personDao.find(Long.valueOf(loginRet.getPersonid()));
		return personService.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView showPassword(@ModelAttribute("person") Person person,@RequestParam("backurl")String backurl,HttpSession session){
		logger.info("showPassword");
		ModelAndView mav=new ModelAndView();
		mav.addObject("person",person);
		mav.addObject("msg",new SessionMPR(session).getMessage());
		mav.addObject("backurl",backurl);
		mav.setViewName("password/password");
		return mav;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public ModelAndView savePassword(@ModelAttribute("person") Person person,@RequestParam("backurl")String backurl,PasswordPage page,HttpSession session){
		logger.info("savePassword");
		page.setRealcsr(person.getChkstr());
		page.verifyPassword();
		
		ModelAndView mav=new ModelAndView();
		if(page.isPass()){
			person.setChkstr(page.getConfirmcsr());
			personService.save(person);
			SessionUtil util=new SessionUtil(session);
			util.remove("loginRet");
			mav.addObject("msg","密码修改成功！");
			mav.setViewName("password/sucess");
		}else{
			MessagePasser mpr=new SessionMPR(session);
			mpr.setMessage(page.getMessage());
			mav.setViewName("redirect:password?backurl="+backurl);
		}
	
		return mav;
	}
	

}
