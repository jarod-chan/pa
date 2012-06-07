package cn.fyg.pa.interfaces.module.system.login;

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
import cn.fyg.pa.domain.model.person.ManageEnum;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;
import cn.fyg.pa.interfaces.module.shared.tool.SessionUtil;


@Controller
@RequestMapping("/login")
public class LoginCtl {

	private static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	@Resource
	private LoginService loginService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getLogin() {
		logger.info("getLogin");
		return "redirect:first";
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
			
			List<UrlNameBean> funcList=getFuncList(loginRetBean);
			new SessionUtil(request).setValue("funcList", funcList);
			List<UrlNameBean> queryList=getQueryList(loginRetBean);
			new SessionUtil(request).setValue("queryList", queryList);
			
			return dispatcherMav(loginRetBean);
		}
				
		return reLoginMav(loginBean);
	}

	private List<UrlNameBean> getQueryList(LoginRetBean loginRetBean) {
		String personId=loginRetBean.getPersonid();
		List<UrlNameBean> menuList=new ArrayList<UrlNameBean>();
		if(loginRetBean.getMange().equals("A")){
		}
		if(loginRetBean.getMange().equals("G")){
			menuList.add(new UrlNameBean("部门月度计划历史",String.format("gmange/%s/idrmonthplan/history",personId)));
			menuList.add(new UrlNameBean("公司考核情况查询",String.format("gmange/%s/totalreport",personId)));
		}
		if(loginRetBean.getMange().equals("Y")){
			if(isSpecialPerson(personId)){
				menuList.add(new UrlNameBean("部门月度计划历史",String.format("mange/%s/idrmonthplan/history",personId)));
			}else{				
				menuList.add(new UrlNameBean("部门月度计划历史",String.format("mange/%s/idrmonthplan/history",personId)));
				menuList.add(new UrlNameBean("员工工作评价历史",String.format("mange/%s/monthchk/histroy",personId)));
			}
		}
		if (loginRetBean.getMange().equals("N")) {
			menuList.add(new UrlNameBean("月度工作历史",String.format("person/%s/monthchk/histroy",personId)));
			menuList.add(new UrlNameBean("部门计划查看",String.format("person/%s/monthchk/idrmonthplan",personId)));
		}
		
		
		return menuList;
	}

	//XXX 返回用户的菜单
	private List<UrlNameBean> getFuncList(LoginRetBean loginRetBean) {
		String personId=loginRetBean.getPersonid();
		List<UrlNameBean> menuList=new ArrayList<UrlNameBean>();
		if(loginRetBean.getMange().equals("A")){
		}
		if(loginRetBean.getMange().equals("G")){
			menuList.add(new UrlNameBean("部门月度计划",String.format("gmange/%s/idrmonthplan",personId)));
		}
		if(loginRetBean.getMange().equals("Y")){
			if(isSpecialPerson(personId)){
				menuList.add(new UrlNameBean("部门月度计划",String.format("mange/%s/idrmonthplan",personId)));
			}else{				
				menuList.add(new UrlNameBean("部门月度计划",String.format("mange/%s/idrmonthplan",personId)));
				menuList.add(new UrlNameBean("员工工作评价",String.format("mange/%s/monthchk",personId)));
				menuList.add(new UrlNameBean("员工年度考核",String.format("mange/%s/yearchk",personId)));
			}
		}
		if (loginRetBean.getMange().equals("N")) {
			menuList.add(new UrlNameBean("月度工作任务",String.format("person/%s/monthchk",personId)));
			menuList.add(new UrlNameBean("年终员工考核",String.format("person/%s/yearchk",personId)));
		}
		
		
		return menuList;
	}
	
	//如果是【办公室】【 产品部】 的两个特殊人员，则做特殊处理
	private boolean isSpecialPerson(String personId){
		if(personId==null) return false;
		if(personId.equals("101")||personId.equals("102")){
			return true;
		}
		return false;
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
		mav.setViewName("first");
		mav.addObject("loginPage",loginBean);
		mav.addObject(Constant.MESSAGE_NAME,"用户名或者密码错误!");
		return mav;
	}

}
