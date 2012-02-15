package cn.fyg.pa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.dao.PersonDao;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.tool.Constant;
import cn.fyg.pa.tool.CookieUtil;


@Controller
@RequestMapping("/")
public class LoginCtl {

	private static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	@Autowired
	private PersonDao fypersonDao;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		logger.info("show login page!");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	/**
	 * @param pageperson
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute Person pageperson,HttpServletRequest request, HttpServletResponse response) {
		logger.info("action login");
		if(isAdmin(pageperson)){
			return adminMAV(request,response,pageperson);
		}
		Person retperson=fypersonDao.findByName(pageperson.getName());
		if(checkManage(pageperson,retperson)){
			return manageMav(request,response,retperson);
		}
		
		if(checkPerson(pageperson,retperson)){
			return sucessMav(request,response, retperson);
		}
		
		return failMav(pageperson);
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
	
	private ModelAndView sucessMav(HttpServletRequest request,HttpServletResponse response,
			Person retperson) {
		CookieUtil.setChkstrCookie(request, response, retperson.getChkstr());
		ModelAndView mav = new ModelAndView();
		//mav.setViewName("redirect:fycheck/list?personId="+retperson.getId());
		mav.setViewName("redirect:monthchk/new?personId="+retperson.getId());
		return mav;
	}
	
	private boolean checkManage(Person pageperson,Person realperson){
		if(realperson==null) return false;
		if(realperson.getManage()==null) return false;
		if(!realperson.getManage().equals("Y")) return false;
		if(realperson.getChkstr()==null) return false;
		if(!realperson.getChkstr().equals(pageperson.getChkstr())) return false;
		return true;
	}
	
	private ModelAndView manageMav(HttpServletRequest request,HttpServletResponse response, Person retperson) {
		CookieUtil.setChkstrCookie(request, response, retperson.getChkstr());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:fymanage/list?personId="+retperson.getId());
		return mav;
	}
	
	private ModelAndView failMav(Person pageperson) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg","用户名或者密码错误!");
		mav.addObject("username",pageperson.getName());
		mav.addObject("password",pageperson.getChkstr());
		mav.setViewName("login");
		return mav;
	}

}
