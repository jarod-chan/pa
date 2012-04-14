package cn.fyg.pa.interfaces.tool;

import org.springframework.web.servlet.ModelAndView;

public class CommonModelAndView {
	
	public static  ModelAndView getHomeModelAndView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:../");
		return mav;
	}

}
