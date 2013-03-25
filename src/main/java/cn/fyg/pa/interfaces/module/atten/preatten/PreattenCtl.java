package cn.fyg.pa.interfaces.module.atten.preatten;

import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.pa.application.PreattenService;
import cn.fyg.pa.domain.model.atten.common.AMPM;
import cn.fyg.pa.domain.model.atten.preatten.Preatten;
import cn.fyg.pa.domain.model.atten.preatten.Prestate;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.shared.Result;
import cn.fyg.pa.infrastructure.util.DateTool;
import cn.fyg.pa.interfaces.module.shared.bean.YearAndMonthBean;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/atten/{personId}/preatten")
public class PreattenCtl {
	
	private interface Page{
		String PATH = "atten/preatten/";
		String LIST = PATH + "list";
		String NEW = PATH + "new";
		String VIEW = PATH + "view";
	}
	
	@Resource
	SessionUtil sessionUtil;
	@Resource
	PersonRepository personRepository;
	@Resource
	PreattenService preattenService;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(YearAndMonthBean queryBean,@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		List<Preatten> preattenList=preattenService.getBusioutByPersonAndYearAndMonth(person, queryBean.getYear(), queryBean.getMonth());
		map.put("dateTool", new DateTool());
		map.put("queryBean", queryBean);
		map.put("preattenList", preattenList);
		return Page.LIST;
	}
	
	@RequestMapping(value="new",method=RequestMethod.GET)
	public String toNew(@ModelAttribute("person")Person person,Map<String,Object> map){
		Object obj=sessionUtil.getValueAndRemove("preatten");
		Preatten preatten=(obj==null?preattenService.create(person):(Preatten)obj);
		map.put("preatten", preatten);
		map.put("ampms", AMPM.values());
		map.put("person", person);
		map.put("dayList", new DateTool().theDaysAfterToday());
		return Page.NEW;
	}
	
	@RequestMapping(value="commit",method=RequestMethod.POST)
	public String commit(HttpServletRequest request,@ModelAttribute("person")Person person,Map<String,Object> map,RedirectAttributes redirectAttributes){
		Preatten preatten = preattenService.create(person);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(preatten);
		binder.bind(request);
		
		Result result = preattenService.verify(preatten);
		if(result.notPass()){
			redirectAttributes.addFlashAttribute(Constant.MESSAGE_NAME,result.cause());
			sessionUtil.setValue("preatten", preatten);
			return "redirect:new";
		}
		preatten.setNo(preattenService.getNextNo(person, preatten.getDayitem().getYear(), preatten.getDayitem().getMonth()));
		preatten.setPerson(person);
		preatten.setState(Prestate.committed);
		preatten.setCommitDate(new Date());
		preattenService.save(preatten);
		return "redirect:list";
	}
	
	@RequestMapping(value="view/{preattenId}",method=RequestMethod.GET)
	public String toView( @PathVariable("preattenId")Long preattenId,YearAndMonthBean queryBean,Map<String,Object> map){
		Preatten preatten =preattenService.find(preattenId);
		map.put("preatten", preatten);
		map.put("queryBean", queryBean);
		return Page.VIEW;
	}
}
