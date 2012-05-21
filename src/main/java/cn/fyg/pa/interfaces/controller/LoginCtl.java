package cn.fyg.pa.interfaces.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.application.LoginService;
import cn.fyg.pa.domain.person.ManageEnum;
import cn.fyg.pa.domain.person.PersonRepository;
import cn.fyg.pa.interfaces.bean.LoginBean;
import cn.fyg.pa.interfaces.bean.LoginRetBean;
import cn.fyg.pa.interfaces.bean.UrlNameBean;
import cn.fyg.pa.interfaces.tool.Dispatcher;
import cn.fyg.pa.interfaces.tool.SessionUtil;


@Controller
@RequestMapping("/login")
public class LoginCtl {

	private static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	@Resource
	private LoginService loginService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getLogin() {
		logger.info("getLogin");
		return "login";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView postLogin(LoginBean loginBean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("postLogin");
		
		LoginRetBean loginRetBean=loginService.checkLoginPerson(loginBean);
				
		if(loginRetBean.isPass()){
			loginRetBean=doChangeForSpecialPerson(loginBean,loginRetBean);
			new SessionUtil(request).setValue("loginRet",loginRetBean);
			String loginInfo = getLoginInfo(loginRetBean);
			new SessionUtil(request).setValue("loginInfo", loginInfo);
			List<UrlNameBean> menuList=getMenuList(loginRetBean);
			new SessionUtil(request).setValue("menuList", menuList);
			return dispatcherMav(loginRetBean);
		}
				
		return reLoginMav(loginBean);
	}

	//XXX 返回用户的菜单
	private List<UrlNameBean> getMenuList(LoginRetBean loginRetBean) {
		String personId=loginRetBean.getPersonid();
		List<UrlNameBean> menuList=new ArrayList<UrlNameBean>();
		if(loginRetBean.getMange().equals("A")){
		}
		if(loginRetBean.getMange().equals("G")){
			menuList.add(new UrlNameBean("部门工作执行",String.format("gmange/%s/idrmonthplan",personId)));
		}
		if(loginRetBean.getMange().equals("Y")){
			menuList.add(new UrlNameBean("员工工作评价",String.format("mange/%s/monthchk",personId)));
			menuList.add(new UrlNameBean("部门工作执行",String.format("mange/%s/idrmonthplan",personId)));
			menuList.add(new UrlNameBean("员工年度考核",String.format("mange/%s/yearchk",personId)));
		}
		if (loginRetBean.getMange().equals("N")) {
			menuList.add(new UrlNameBean("月度工作任务",String.format("person/%s/monthchk",personId)));
			menuList.add(new UrlNameBean("年终员工考核",String.format("person/%s/yearchk",personId)));
		}
		
		
		return menuList;
	}


	//XXX  传递登录人员信息的session
	private String getLoginInfo(LoginRetBean loginRetBean) {
		String loginInfo="";
		String mangeName=ManageEnum.valueOf(loginRetBean.getMange()).getName();
		String userName=loginRetBean.getName();
		loginInfo=mangeName+":"+userName;
		return loginInfo;
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
