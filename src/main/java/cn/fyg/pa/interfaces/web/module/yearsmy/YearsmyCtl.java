package cn.fyg.pa.interfaces.web.module.yearsmy;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.pa.application.PersonSummaryService;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.summary.Content;
import cn.fyg.pa.domain.model.summary.PersonSummary;
import cn.fyg.pa.domain.model.summary.SummaryEnum;
import cn.fyg.pa.interfaces.web.advice.personin.annotation.PersonIn;
import cn.fyg.pa.interfaces.web.shared.message.impl.SessionMPR;

/**
 * @author jhon.chen@gmail.com
 */
@Controller
@RequestMapping({"/yearsmy" })
public class YearsmyCtl {
	
	/**
	 * 固定年份
	 * 后续可能改成从参数取
	 */
	public static final Long FIX_YEAR=2014L;
	
	private static final String PATH = "summary/";
	private interface Page {
		String SUMMARY = PATH + "summary";
		String VIEW = PATH + "view";
	}
	
	@Resource
	PersonRepository personRepository;
	@Resource
	PersonSummaryService personSummaryService;
	

	
	@RequestMapping(value="",method=RequestMethod.GET)
	@PersonIn(0)
	public String toEdit(Person person,Map<String,Object> map,HttpSession session){
		PersonSummary personSummary = personSummaryService.findAndCreate(FIX_YEAR, person.getId());
		map.put("personSummary", personSummary);
		map.put("person", person);
		map.put("message",new SessionMPR(session).getMessage());
		
		return personSummary.getSummaryEnum()==SummaryEnum.commit?Page.VIEW:Page.SUMMARY;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(PersonSummary personSummary,HttpSession session){
		personSummary.setSummaryEnum(SummaryEnum.save);
		personSummaryService.save(personSummary);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:/yearsmy"; 
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(PersonSummary personSummary,HttpSession session){
		personSummary.setSummaryEnum(SummaryEnum.commit);
		personSummaryService.save(personSummary);
		new SessionMPR(session).setMessage("提交成功！");
		return "redirect:/yearsmy"; 
	}
	
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(Content content,HttpSession session){
		personSummaryService.save(content);
		return "success";
	}
	
	@RequestMapping(value="{year}",method=RequestMethod.GET)
	@PersonIn(1)
	public String toViewYear(@PathVariable("year")Long year,Person person,Map<String,Object> map,HttpSession session){
		PersonSummary personSummary = personSummaryService.find(year, person.getId());
		map.put("personSummary", personSummary);
		map.put("person", person);
		map.put("message",new SessionMPR(session).getMessage());
		
		return Page.VIEW;
	}
}
