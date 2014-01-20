package cn.fyg.pa.interfaces.module.gmange.report;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.personin.annotation.PersonIn;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/gmange/{personId}/totalreport")
public class TotalReportCtl {
	
	@Resource
	PersonRepository personRepository;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	@PersonIn(0)
	public String toList(Person person,Map<String,Object> map,HttpSession session){
		map.put("person",person);
		map.put(Constant.MESSAGE_NAME,new SessionMPR(session).getMessage());
		return "totalreport/list";
	}

}
