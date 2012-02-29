package cn.fyg.pa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.enums.ManageEnum;
import cn.fyg.pa.page.LoginPage;
import cn.fyg.pa.page.LoginRet;
import cn.fyg.pa.service.PersonService;
import cn.fyg.pa.tool.Constant;
import cn.fyg.pa.tool.CookieUtil;
import cn.fyg.pa.tool.SessionUtil;


@Controller
@RequestMapping("/login")
public class LoginCtl {

	private static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getLogin() {
		logger.info("getLogin");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView postLogin(LoginPage loginPage,HttpServletRequest request, HttpServletResponse response) {
		logger.info("postLogin");
		
		LoginRet loginRet=personService.checkLoginPerson(loginPage);
		
		if(loginRet.isPass()){
			new SessionUtil(request).setValue("loginRet",loginRet);
			return dispatcherMav();
		}
				
		return reLoginMav(loginPage);
	}

	private ModelAndView dispatcherMav() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("forward:/dispatcher");
		return mav;
	}
	
	private ModelAndView reLoginMav(LoginPage loginPage) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("login");
		mav.addObject("loginPage",loginPage);
		mav.addObject("msg","用户名或者密码错误!");
		return mav;
	}

	
	

	private boolean isAdmin(Person pageperson) {
		if (pageperson.getName().equals("admin")
				&& pageperson.getChkstr().equals(Constant.ADMIN_PASSWORD))
			return true;
		return false;
	}
	
	private ModelAndView adminMAV(HttpServletRequest request,HttpServletResponse response, Person pageperson) {
		CookieUtil.setChkstrCookie(request, response, pageperson.getChkstr());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:admin/all");
		return mav;
	}
	
	private boolean checkPerson(Person pageperson,Person realperson){
		if(realperson==null) return false;
		if(realperson.getChkstr()==null) return false;
		if(!realperson.getChkstr().equals(pageperson.getChkstr())) return false;
		return true;
	}
	
	private ModelAndView personMav(HttpServletRequest request,HttpServletResponse response,
			Person retperson) {
		CookieUtil.setChkstrCookie(request, response, retperson.getChkstr());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:fycheck/list?personId="+retperson.getId());
		//mav.setViewName("redirect:/person/"+retperson.getId()+"/monthchk");
		return mav;
	}
	
	private boolean checkManage(Person pageperson,Person realperson){
		if(realperson==null) return false;
		if(realperson.getManage()==null) return false;
		if(!realperson.getManage().equals(ManageEnum.Y)) return false;
		if(realperson.getChkstr()==null) return false;
		if(!realperson.getChkstr().equals(pageperson.getChkstr())) return false;
		return true;
	}
	
	private ModelAndView manageMav(HttpServletRequest request,HttpServletResponse response, Person retperson) {
		CookieUtil.setChkstrCookie(request, response, retperson.getChkstr());
		ModelAndView mav = new ModelAndView();
		//mav.setViewName("redirect:fymanage/list?personId="+retperson.getId());
		mav.setViewName("redirect:/mange/"+retperson.getId()+"/monthchk");
		return mav;
	}
	

}
