package cn.fyg.pa.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PaCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(PaCtl.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		logger.info("home");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:login");
		return mav;
	}
	
	@RequestMapping(value = "fail", method = RequestMethod.GET)
	public ModelAndView fail(Model model) {
		logger.info("fail");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fail");
		return mav;
	}

}
