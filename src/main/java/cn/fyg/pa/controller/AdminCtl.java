package cn.fyg.pa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/admin")
public class AdminCtl {
	private static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	@RequestMapping(value="/all")
	public ModelAndView list() {
		//version devone
		logger.info("show all");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/all");
		return mav;
	}
}
