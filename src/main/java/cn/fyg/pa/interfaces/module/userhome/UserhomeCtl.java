package cn.fyg.pa.interfaces.module.userhome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/userhome")
public class UserhomeCtl {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toUserhome() {
		return "userhome/home";
	}

}
