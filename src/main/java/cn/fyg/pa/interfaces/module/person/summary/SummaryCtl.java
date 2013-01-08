package cn.fyg.pa.interfaces.module.person.summary;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;

@Controller
@RequestMapping("/person/{personId}/summary")
public class SummaryCtl {
	
	/**
	 * 固定年份
	 * 后续可能改成从参数取
	 */
	private static final Long FIX_YEAR=2012L;
	
	private static final String PATH = "summary/";
	private interface Page {
		String SUMMARY = PATH + "summary";
		String VIEW = PATH + "view";
	}
	
	@Resource
	PersonRepository personRepository;
	@Resource
	PersonSummaryService personSummaryService;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toEdit(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		PersonSummary personSummary = personSummaryService.findAndCreate(FIX_YEAR, person.getId());
		map.put("personSummary", personSummary);
		map.put("person", person);
		map.put("message",new SessionMPR(session).getMessage());
		return personSummary.getSummaryEnum()==SummaryEnum.save?Page.SUMMARY:Page.VIEW;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(PersonSummary personSummary,HttpSession session){
		personSummary.setSummaryEnum(SummaryEnum.save);
		personSummaryService.save(personSummary);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:../summary"; 
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(PersonSummary personSummary,HttpSession session){
		personSummary.setSummaryEnum(SummaryEnum.commit);
		personSummaryService.save(personSummary);
		new SessionMPR(session).setMessage("提交成功！");
		return "redirect:../summary"; 
	}
	
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(Content content,HttpSession session){
		personSummaryService.save(content);
		return "success";
	}
	
	
}
