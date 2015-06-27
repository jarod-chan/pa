package cn.fyg.pa.interfaces.web.module.monthsmy.manage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.fyg.pa.application.MonthChkService;
import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.shared.state.StateChangeException;
import cn.fyg.pa.interfaces.web.advice.personin.annotation.PersonIn;
import cn.fyg.pa.interfaces.web.shared.bean.YearAndMonthBean;
import cn.fyg.pa.interfaces.web.shared.bean.YearAndPrevMonth;
import cn.fyg.pa.interfaces.web.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.web.shared.session.SessionUtil;
import cn.fyg.pa.interfaces.web.shared.tool.DateTool;

/**
 * @author jhon.chen@gmail.com
 *
 */
@Controller
@RequestMapping("monthsmy/manage")
@SessionAttributes("monthplan_manage") 
public class MangeMonthChkCtl {
	
	private static final String PATH="mange/monthchk/";
	private interface Page {
		String LIST     = PATH + "list";
		String EVALUATE = PATH + "evaluate";
		String HISTROY  = PATH + "histroy";
		String VIEW = PATH + "view";
	}
	

	@Resource
	SessionUtil sessionUtil;
	@Resource
	PersonRepository personRepository;
	@Resource
	MonthChkService monthChkService;
	@Resource
	MonthChkRepository monthChkRepository;
	@Resource
	MangeMonthChkFacade mangeMonthChkFacade;
	
	@ModelAttribute("monthplan_manage")
	public YearAndPrevMonth monthplan_manage(){
		return new YearAndPrevMonth();
	}
	
	@RequestMapping(value="",method={RequestMethod.GET,RequestMethod.POST})
	@PersonIn(1)
	public String toList(@ModelAttribute("monthplan_manage")YearAndPrevMonth monthplan_manage,Person person,Map<String,Object> map,HttpSession session){
		List<MonthChk> monthChks=mangeMonthChkFacade.getAllPersonMonthChkByPeriod(monthplan_manage.getYear(),monthplan_manage.getMonth(),person.getDepartment());
		map.put("dateTool", new DateTool());
		map.put("mange", person);
		map.put("monthChks", monthChks);
		map.put("message",new SessionMPR(session).getMessage());
		return Page.LIST;
	}

	
	@RequestMapping(value="/{monthchkId}",method=RequestMethod.GET)
	@PersonIn(0)
	public String toEvaluate(Person person,@PathVariable("monthchkId") Long monthchkId,Map<String,Object> map,HttpSession session){
		MonthChk monthChk=monthChkRepository.find(monthchkId);
		map.put("mange", person);
		map.put("monthChk", monthChk);
		map.put("message",new SessionMPR(session).getMessage());
		return Page.EVALUATE;
	}
	
	@RequestMapping(value="/{monthchkId}/save",method=RequestMethod.POST)
	@PersonIn(0)
	public String save(Person person,@PathVariable("monthchkId") Long monthchkId,HttpServletRequest request,HttpSession session){
		MonthChk monthChk=monthChkRepository.find(monthchkId);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(monthChk);//XXX 能否修改这里的逻辑？
		binder.bind(request);	
		monthChkService.save(monthChk);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:/monthsmy/manage/"+monthchkId;
	}

	@RequestMapping(value="/{monthchkId}/finish",method=RequestMethod.POST)
	@PersonIn(0)
	public String finish(Person person,@PathVariable("monthchkId") Long monthchkId, HttpServletRequest request,HttpSession session){
		MonthChk monthChk=monthChkRepository.find(monthchkId);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(monthChk);//XXX 能否修改这里的逻辑？
		binder.bind(request);	
		monthChkService.save(monthChk);
		String message="单据已完成！";
		try {
			monthChkService.next(monthChk.getId());
		} catch (StateChangeException e) {
			message=String.format("完成失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:/monthsmy/manage/";
	}


	@RequestMapping(value="/{monthchkId}/back",method=RequestMethod.POST)
	@PersonIn(0)
	public String back(Person person,@PathVariable("monthchkId") Long monthchkId,HttpSession session){
		String message="单据已打回！";
		try {
			monthChkService.back(monthchkId);
		} catch (StateChangeException e) {
			message=String.format("打回失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:/monthsmy/manage";
	}
	


	@RequestMapping(value="/histroy",method=RequestMethod.GET)
	@PersonIn(1)
	public String histroy(YearAndMonthBean queryBean,Person person,Map<String,Object> map){
		List<MonthChk> monthChks=monthChkRepository.getMonthChkByPeriodAndDepartmentAndState(queryBean.getYear(), queryBean.getMonth(),person.getDepartment(), MonthChkEnum.FINISHED);
		map.put("dateTool", new DateTool());
		map.put("mange", person);
		map.put("monthChks", monthChks);
		map.put("queryBean", queryBean);
		return Page.HISTROY;
	}
	
	@RequestMapping(value="{monthchkId}/view",method=RequestMethod.GET)
	@PersonIn(0)
	public String toView(Person person,@PathVariable("monthchkId") Long monthchkId,Map<String,Object> map){
		MonthChk monthChk=monthChkRepository.find(monthchkId);
		map.put("monthChk", monthChk);
		return Page.VIEW;
	}
}
