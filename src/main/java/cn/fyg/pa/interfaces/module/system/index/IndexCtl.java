package cn.fyg.pa.interfaces.module.system.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class IndexCtl {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "redirect:first";
	}
	
	@RequestMapping(value = "first")
	public String first() {
		return "first";
	}
		
	@RequestMapping(value = "fail", method = RequestMethod.GET)
	public String fail() {
		return "fail";
	}

}
