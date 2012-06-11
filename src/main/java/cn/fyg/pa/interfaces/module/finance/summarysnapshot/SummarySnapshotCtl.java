package cn.fyg.pa.interfaces.module.finance.summarysnapshot;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;

@Controller
@RequestMapping("/finance/{personId}/summarysnapshot")
public class SummarySnapshotCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(SummarySnapshotCtl.class);
	
	private interface Page {
		String PATH = "finance/summarysnapshot/";
		String LIST     = PATH + "list";
		String RECEIVE     = PATH + "receive";
	}
	
	@Resource
	PersonRepository personRepository;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String  toList(Map<String,Object> map,HttpSession session){
		
		return Page.LIST;
	}
	
	
	@RequestMapping(value="/receive",method=RequestMethod.GET)
	public String toReceive(Map<String,Object> map,HttpSession session){
		logger.info("toReceive");
		
		return Page.RECEIVE;
	}
	
	

}
