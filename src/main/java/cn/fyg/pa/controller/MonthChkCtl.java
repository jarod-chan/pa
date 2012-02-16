package cn.fyg.pa.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/monthchk")
public class MonthChkCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(MonthChkCtl.class);
	
	@RequestMapping(value="/{person}")
	public ModelAndView _new(){
		logger.info("_new");

		ModelAndView mav=new ModelAndView();
		mav.setViewName("monthchk/new");
		return mav;
	}
}
