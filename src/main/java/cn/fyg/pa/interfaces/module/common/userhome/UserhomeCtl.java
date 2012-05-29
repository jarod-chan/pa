package cn.fyg.pa.interfaces.module.common.userhome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/common/userhome/{personId}")
public class UserhomeCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(UserhomeCtl.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toUserhome() {
		logger.info("toUserhome");
		return "userhome/home";
	}

}
