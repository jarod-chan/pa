package cn.fyg.pa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.dao.PersonDao;
import cn.fyg.pa.page.SendMailPage;

@Controller
@RequestMapping("/fetchcsr")
public class FetchCsrCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(FetchCsrCtl.class);
	
	@Autowired
	private PersonDao personDao;
	
	@RequestMapping(value="")
	public ModelAndView show(){
		logger.info("show");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("fetchcsr/mail");
		return mav;
	}
	
	 @RequestMapping(value="",method=RequestMethod.POST)  
	public ModelAndView sendMail(SendMailPage sendMailPage){
		logger.info("sendMail");
		if(!sendMailPage.checkPage()){
			return getFailMav();
		}
		
		String mail=personDao.getMailAdressByUsername(sendMailPage.getUsername());
		if(mail==null){
			return getFailMav();
		}
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("fetchcsr/mail");
		return mav;
		
	}

	private ModelAndView getFailMav() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("fetchcsr/mail");
		mav.addObject("msg","该用户不存在");
		return mav;
	}

}
