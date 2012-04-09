package cn.fyg.pa.application.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.application.bean.LoginBean;
import cn.fyg.pa.application.bean.LoginRetBean;
import cn.fyg.pa.domain.service.PersonService;
import cn.fyg.pa.tool.Dispatcher;
import cn.fyg.pa.tool.SessionUtil;


@Controller
@RequestMapping("/login")
public class LoginCtl {

	private static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	@Resource
	private PersonService personService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getLogin() {
		logger.info("getLogin");
		return "login";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView postLogin(LoginBean loginBean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("postLogin");
		
		LoginRetBean loginRetBean=personService.checkLoginPerson(loginBean);
				
		if(loginRetBean.isPass()){
			loginRetBean=doChangeForSpecialPerson(loginBean,loginRetBean);
			new SessionUtil(request).setValue("loginRet",loginRetBean);
			return dispatcherMav(loginRetBean);
		}
				
		return reLoginMav(loginBean);
	}

	//XXX 此处待修改
	private LoginRetBean doChangeForSpecialPerson(LoginBean loginBean,
			LoginRetBean loginRet) {
		String username=loginBean.getUsername().trim();
		if(username.equals("牟一琦")||username.equals("陆兆贤")){
			loginRet.setMange(loginBean.getSpecialPerson());
		}
		return loginRet;
	}


	private ModelAndView dispatcherMav(LoginRetBean loginRet) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName(Dispatcher.dispatcher(loginRet));
		return mav;
	}
	
	private ModelAndView reLoginMav(LoginBean loginBean) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("login");
		mav.addObject("loginPage",loginBean);
		mav.addObject("msg","用户名或者密码错误!");
		return mav;
	}

}
