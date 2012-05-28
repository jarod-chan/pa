package cn.fyg.pa.interfaces.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.fyg.pa.interfaces.bean.LoginRetBean;

/**
 *根据登录员工的角色来分发url
 */
public class Dispatcher {
	
	public static final Logger logger=LoggerFactory.getLogger(Dispatcher.class);
	
	public static String dispatcher(LoginRetBean loginRet) {
		logger.info("dispatcher");
		
		if(loginRet.getMange().equals("A")){
			return "redirect:admin/all";
		}
		if(loginRet.getMange().equals("G")){
			return "redirect:gmange/"+loginRet.getPersonid()+"/idrmonthplan";
		}
		if(loginRet.getMange().equals("Y")){
			return "redirect:mange/"+loginRet.getPersonid()+"/idrmonthplan";
		}
		if (loginRet.getMange().equals("N")) {
			return "redirect:person/"+loginRet.getPersonid()+"/monthchk";
		}
		
		return "redirect:/login";
	}

}
