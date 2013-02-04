package cn.fyg.pa.interfaces.module.person.summary;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.PersonSummaryService;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.summary.PersonSummary;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.system.login.LoginRetBean;

@Controller
@RequestMapping({"/person/{personId}/summary" ,"/mange/{personId}/summary"})
public class SummaryCtl {
	
	/**
	 * 固定年份
	 * 后续可能改成从参数取
	 */
	private static final Long FIX_YEAR=2012L;
	
	private static final String PATH = "summary/";
	private interface Page {
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
		
		//TODO url特殊如理
		map.put("urlRole", "person");
		Object loginRetObj=session.getAttribute("loginRet");
		if(loginRetObj!=null){	
			LoginRetBean loginRet=(LoginRetBean)loginRetObj;
			if(loginRet.getMange().equals("Y")){
				map.put("urlRole", "mange");
			}
		}
		
		return Page.VIEW;
	}

	
	
}
