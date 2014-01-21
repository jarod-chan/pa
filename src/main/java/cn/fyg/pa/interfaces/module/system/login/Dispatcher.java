package cn.fyg.pa.interfaces.module.system.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *根据登录员工的角色来分发url
 */
public class Dispatcher {
	
	public static final Logger logger=LoggerFactory.getLogger(Dispatcher.class);
	
	public static String dispatcher(LoginRetBean loginRet) {
		
		if(loginRet.getMange().equals("A")){
			return "redirect:admin/all";
		}
		if(loginRet.getMange().equals("G")){
			return "redirect:monthplan/gm";
		}
		if(loginRet.getMange().equals("Y")){
			return "redirect:monthplan";
		}
		if (loginRet.getMange().equals("N")) {
			return "redirect:monthsmy";
		}
		if (loginRet.getMange().equals("F")) {
			return "redirect:finance/"+loginRet.getPersonid()+"/summarysnapshot";
		}
		
		return "redirect:/login";
	}

}
