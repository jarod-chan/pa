package cn.fyg.pa.interfaces.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PaCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(PaCtl.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home() {
		logger.info("home");
		return "redirect:login";
	}
	
	@RequestMapping(value = "fail", method = RequestMethod.GET)
	public String fail() {
		logger.info("fail");
		return "fail";
	}

}
