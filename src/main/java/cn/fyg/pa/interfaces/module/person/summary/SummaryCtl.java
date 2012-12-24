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
import cn.fyg.pa.interfaces.module.system.login.LoginRetBean;

/**
 * @author jhon.chen@gmail.com
 *TODO  要匹配两个url，在页面显示及url出要特殊处理，注意todo 注释处
 */
@Controller
@RequestMapping({"/person/{personId}/summary" ,"/mange/{personId}/summary"})
public class SummaryCtl {
	
	/**
	 * 固定年份
	 * 后续可能改成从参数取
	 */
	public static final Long FIX_YEAR=2012L;
	
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
		
		//TODO url特殊如理
		map.put("urlRole", "person");
		Object loginRetObj=session.getAttribute("loginRet");
		if(loginRetObj!=null){	
			LoginRetBean loginRet=(LoginRetBean)loginRetObj;
			if(loginRet.getMange().equals("Y")){
				map.put("urlRole", "mange");
			}
		}
		
		return personSummary.getSummaryEnum()==SummaryEnum.commit?Page.VIEW:Page.SUMMARY;
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
		//TODO 隐藏总结提示信息
		SummaryCommon.hideSummaryInfo(session);
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
