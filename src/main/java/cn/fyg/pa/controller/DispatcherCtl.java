package cn.fyg.pa.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cn.fyg.pa.page.LoginRet;
import cn.fyg.pa.tool.SessionUtil;

@Controller
@RequestMapping("/dispatcher")
public class DispatcherCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(DispatcherCtl.class);
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String dispatcher(HttpServletRequest request) {
		logger.info("dispatcher");
		
		LoginRet loginRet=new SessionUtil(request).getValue("loginRet", LoginRet.class);
		
		if(loginRet.getMange().equals("A")){
			return "redirect:admin/all";
		}
		if(loginRet.getMange().equals("Y")){
			return "redirect:/mange/"+loginRet.getPersonid()+"/monthchk";
		}
		if (loginRet.getMange().equals("N")) {
			return "redirect:/person/"+loginRet.getPersonid()+"/monthchk";
		}
		
		return "redirect:/login";
	}

}
