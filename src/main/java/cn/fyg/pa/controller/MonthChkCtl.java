package cn.fyg.pa.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.dao.MonthChkDao;
import cn.fyg.pa.dao.PersonDao;
import cn.fyg.pa.dao.WorkTypeDao;
import cn.fyg.pa.message.MessagePasser;
import cn.fyg.pa.message.imp.SessionMPR;
import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.MonthChkItem;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.WorkType;
import cn.fyg.pa.model.enums.StateEnum;
import cn.fyg.pa.page.MonthChkPage;

@Controller
@RequestMapping("/person/{personId}/monthchk")
public class MonthChkCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(MonthChkCtl.class);
	
	@Autowired
	PersonDao personDao;
	
	@Autowired
	MonthChkDao monthChkDao;
	
	@Autowired
	WorkTypeDao workTypeDao;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personDao.find(personId);
	}
	
	
	@RequestMapping(value="")
	public ModelAndView monthchk(@ModelAttribute("person")Person person,HttpSession session){
		logger.info("monthchk");
		MonthChk monthChk=monthChkDao.getCurrMonthChk(person);
		Person mange=personDao.findDeptMange(person.getDepartment());
		List<WorkType> workTypes=workTypeDao.getAll();
		
		ModelAndView mav = getShowMav(monthChk);
		MessagePasser mpr=new SessionMPR(session);
		mav.addObject("msg",mpr.getMessage());
		mav.addObject("mange", mange);
		mav.addObject("workTypes", workTypes);
		return mav;
	}


	/**
	 * 获得默认展示界面
	 * @param monthChk
	 * @return
	 */
	private ModelAndView getShowMav(MonthChk monthChk) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("monthChk", monthChk);
		if(monthChk.getState().equals(StateEnum.SAVED)){
			mav.setViewName("monthchk/edit");
		}else{
			mav.setViewName("monthchk/view");
		}
		return mav;
	}
	
	@RequestMapping(value="/histroy")
	public ModelAndView histroy(@ModelAttribute("person")Person person,HttpSession session){
		logger.info("monthchk");
		List<MonthChk> monthChks=monthChkDao.getAllFinishMonthChkByPerson(person);
		ModelAndView mav=new ModelAndView();
		mav.addObject("monthChks", monthChks);
		mav.addObject("person", person);
		MessagePasser mpr=new SessionMPR(session);
		mav.addObject("msg",mpr.getMessage());
		mav.setViewName("monthchk/histroy");
		return mav;
	}
	
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(MonthChkPage monthChkPage,@ModelAttribute("person")Person person,HttpSession session){
		logger.info("save");
		
		MonthChk monthChk=monthChkDao.getMonthChk(person, monthChkPage.getYear(), monthChkPage.getMonth());
		monthChkPage.updateMonthChk(monthChk);
		
		if(!isCanSave(monthChk)){
			MessagePasser mpr=new SessionMPR(session);
			mpr.setMessage("提交失败[单据重复提交]！");
		}else{			
			monthChk.setState(StateEnum.SAVED);
			monthChk=monthChkDao.save(monthChk);
			MessagePasser mpr=new SessionMPR(session);
			mpr.setMessage("保存成功！");
		}

		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/person/"+person.getId()+"/monthchk");
		return mav;
	}
	
	private boolean isCanSave(MonthChk monthChk) {
		if(!monthChk.getState().equals(StateEnum.SAVED)){
			return false;
		}
		return true;
	}


	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public ModelAndView commit(MonthChkPage monthChkPage,@ModelAttribute("person")Person person,HttpSession session){
		logger.info("commit");
		
		MonthChk monthChk=monthChkDao.getMonthChk(person, monthChkPage.getYear(), monthChkPage.getMonth());
		monthChkPage.updateMonthChk(monthChk);
		
		if(!isCanCommint(monthChk)){
			monthChk.setState(StateEnum.SAVED);
			monthChk=monthChkDao.save(monthChk);
			MessagePasser mpr=new SessionMPR(session);
			mpr.setMessage("提交失败[工作内容和用时都不能为空]！");
		}else{
			monthChk.setState(StateEnum.SUBMITTED);
			monthChk=monthChkDao.save(monthChk);
			MessagePasser mpr=new SessionMPR(session);
			mpr.setMessage("提交成功！");
		}
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/person/"+person.getId()+"/monthchk");
		return mav;
	}


	private boolean isCanCommint(MonthChk monthChk) {
		for(MonthChkItem item:monthChk.getMonthChkItems()){
			if(StringUtils.isBlank(item.getTask())){
				return false;
			}
			if(item.getWorkhour()==null){
				return false;
			}
		}
		return true;
	}
	
	
	
}
