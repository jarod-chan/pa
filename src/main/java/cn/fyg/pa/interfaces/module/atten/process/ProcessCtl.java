package cn.fyg.pa.interfaces.module.atten.process;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;



@Controller
@RequestMapping("/atten/{personId}/task")
public class ProcessCtl {
	
	private static final String PATH = "atten/task/";
	private interface Page {
		String TASK = PATH + "task";
	}
	
	@Autowired
	TaskFacade taskFacade;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	PersonRepository personRepository;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(@ModelAttribute("person")Person person, Map<String,Object> map){
		List<ProcessTaskBean> processTasks = taskFacade.getProcessTasks(person.getKey());
		map.put("processTasks", processTasks);
		return Page.TASK;
	}

}
