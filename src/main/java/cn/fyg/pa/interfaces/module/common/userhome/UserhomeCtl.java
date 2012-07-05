package cn.fyg.pa.interfaces.module.common.userhome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/common/userhome/{personId}")
public class UserhomeCtl {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toUserhome() {
		return "userhome/home";
	}

}
