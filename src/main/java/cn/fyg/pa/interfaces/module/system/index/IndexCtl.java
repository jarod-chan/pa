package cn.fyg.pa.interfaces.module.system.index;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class IndexCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexCtl.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		logger.info("welcome");
		return "redirect:first";
	}
	
	@RequestMapping(value = "/first")
	public String first() {
		logger.info("first");
		return "first";
	}
		
	@RequestMapping(value = "/fail", method = RequestMethod.GET)
	public String fail() {
		logger.info("fail");
		return "fail";
	}

}
