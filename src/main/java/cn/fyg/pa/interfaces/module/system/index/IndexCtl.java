package cn.fyg.pa.interfaces.module.system.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class IndexCtl {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "redirect:first";
	}
	
	@RequestMapping(value = "first")
	public String first(@RequestParam(value="pass",required=false) boolean pass) {
		if(pass){
			return "first";
		};
		return "chose";	
	}
		
	@RequestMapping(value = "fail", method = RequestMethod.GET)
	public String fail() {
		return "fail";
	}

}
