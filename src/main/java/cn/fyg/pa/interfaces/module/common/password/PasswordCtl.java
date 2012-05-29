package cn.fyg.pa.interfaces.module.common.password;

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

import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.person.PersonRepository;
import cn.fyg.pa.infrastructure.message.imp.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;
import cn.fyg.pa.interfaces.module.shared.tool.SessionUtil;

@Controller
@RequestMapping("/common/settings/person/{personId}/password")
public class PasswordCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(PasswordCtl.class);
	
	@Resource
	PersonRepository personRepository;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String showPassword(@ModelAttribute("person") Person person,Map<String,Object> map,HttpSession session){
		logger.info("showPassword");
		map.put("person",person);
		map.put(Constant.MESSAGE_NAME,new SessionMPR(session).getMessage());
		return "password/password";
	}
	

	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String savePassword(@ModelAttribute("person") Person person,PasswordPage page,Map<String,Object> map,HttpSession session){
		logger.info("savePassword");
		page.setRealcsr(person.getChkstr());
		page.verifyPassword();
		
		if(!page.isPass()){
			new SessionMPR(session).setMessage(page.getMessage());
			return "redirect:password";
		}
		
		person.setChkstr(page.getConfirmcsr());
		personRepository.save(person);
		SessionUtil util=new SessionUtil(session);
		util.remove("loginRet");
		util.remove("menuList");
		map.put(Constant.MESSAGE_NAME,"密码修改成功！");
		return "password/sucess";	
	}
	
	

}
