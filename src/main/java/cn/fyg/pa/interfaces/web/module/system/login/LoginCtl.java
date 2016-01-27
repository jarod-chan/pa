package cn.fyg.pa.interfaces.web.module.system.login;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.application.LoginService;
import cn.fyg.pa.domain.model.person.ManageEnum;
import cn.fyg.pa.interfaces.web.shared.session.SessionUtil;
import cn.fyg.pa.interfaces.web.shared.tool.Constant;


@Controller
@RequestMapping("/login")
public class LoginCtl {
	
	@Resource
	private LoginService loginService;
	@Resource
	SessionUtil sessionUtil;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getLogin() {
		return "redirect:first";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView postLogin(LoginBean loginBean,HttpServletRequest request, HttpServletResponse response) {
		
		LoginRetBean loginRetBean=loginService.checkLoginPerson(loginBean);
				
		if(loginRetBean.isPass()){
			sessionUtil.invalidate();
			
			loginRetBean=doChangeForSpecialPerson(loginBean,loginRetBean);
			sessionUtil.setValue("loginRet",loginRetBean);
			String loginInfo = getLoginInfo(loginRetBean);
			sessionUtil.setValue("loginInfo", loginInfo);
			List<UrlNameBean> funcList=getFuncList(loginRetBean);
			sessionUtil.setValue("funcList", funcList);
			List<UrlNameBean> queryList=getQueryList(loginRetBean);
			sessionUtil.setValue("queryList", queryList);
			
			/**
			 * TODO:待修改  当是特殊人员时，不跳转到年终考核页面 　
			*/
			if(isSpecialPerson(loginRetBean.getPersonid())){
				return new ModelAndView("redirect:mange/"+loginRetBean.getPersonid()+"/idrmonthplan");
			}
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
			menuList.add(new UrlNameBean("部门月度计划历史","monthplan/gm/history"));
			menuList.add(new UrlNameBean("公司考核情况查询","totalreport"));
			menuList.add(new UrlNameBean("年度考核评分查询","yearpk/gm"));
		}
		if(loginRetBean.getMange().equals("Y")){
			if(isSpecialPerson(personId)){
				menuList.add(new UrlNameBean("部门月度计划历史","monthplan/history"));
			}else{				
				menuList.add(new UrlNameBean("部门月度计划历史","monthplan/history"));
				menuList.add(new UrlNameBean("员工工作评价历史","monthsmy/manage/histroy"));
				String name=loginRetBean.getName();
				//XXX 办公室  潘普兵 考核结果历史
				if(name.equals("潘普兵")) {
					menuList.add(new UrlNameBean("考核结果历史","monthlog/history"));
				}
			}
		}
		if (loginRetBean.getMange().equals("N")) {
			menuList.add(new UrlNameBean("月度工作历史","monthsmy/histroy"));
			menuList.add(new UrlNameBean("部门计划查看","monthsmy/monthplan"));
			String name=loginRetBean.getName();
			//XXX 办公室  李君查看报表
			if(name.equals("李君")) {
				menuList.add(new UrlNameBean("公司考核情况查询","totalreport"));
			}
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
			menuList.add(new UrlNameBean("部门月度计划","monthplan/gm"));
		}
		if(loginRetBean.getMange().equals("Y")){
			if(isSpecialPerson(personId)){
				menuList.add(new UrlNameBean("部门月度计划","monthplan"));
			}else{				
				menuList.add(new UrlNameBean("部门月度计划","monthplan"));
				menuList.add(new UrlNameBean("月度小结评价","monthsmy/manage"));
				menuList.add(new UrlNameBean("个人述职报告","yearsmy"));
				//关闭年度绩效评价
				//menuList.add(new UrlNameBean("年度绩效评价","mangesc"));
				//XXX 财务部  胡吉运 增加考核结果确认菜单
				String name=loginRetBean.getName();
				if(name.equals("胡吉运")){
					menuList.add(new UrlNameBean("考核结果确认","monthlog/cf"));
				}
				//XXX 办公室  潘普兵 考核结果接收菜单
				if(name.equals("潘普兵")) {
					menuList.add(new UrlNameBean("考核结果接收","monthlog"));
				}
			}
		}
		if (loginRetBean.getMange().equals("N")) {
			menuList.add(new UrlNameBean("月度工作小结","monthsmy"));
			menuList.add(new UrlNameBean("个人述职报告","yearsmy"));
			menuList.add(new UrlNameBean("年度横向评价","personsc"));
			String name=loginRetBean.getName();
			if(name.equals("阮善友")){
				menuList.add(new UrlNameBean("部门月度计划","monthplan"));
				menuList.add(new UrlNameBean("月度小结评价","monthsmy/manage"));
			}
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

	//XXX 陆兆贤拥有副总和部门经理权限
	private LoginRetBean doChangeForSpecialPerson(LoginBean loginBean,
			LoginRetBean loginRet) {
		String username=loginBean.getUsername().trim();
		if(username.equals("陆兆贤")||username.equals("王坚")||username.equals("胡玉友")||username.equals("潘普兵")){
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
