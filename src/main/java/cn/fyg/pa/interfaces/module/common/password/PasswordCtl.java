package cn.fyg.pa.interfaces.module.common.password;

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
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/common/settings/person/{personId}/password")
public class PasswordCtl {
	
	@Resource
	PersonRepository personRepository;
	@Resource
	SessionUtil sessionUtil;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	@PersonIn(0)
	public String showPassword(Person person,Map<String,Object> map,HttpSession session){
		map.put("person",person);
		map.put(Constant.MESSAGE_NAME,new SessionMPR(session).getMessage());
		return "password/password";
	}
	

	
	@RequestMapping(value="",method=RequestMethod.POST)
	@PersonIn(0)
	public String savePassword(Person person,PasswordPage page,Map<String,Object> map,HttpSession session){
		page.setRealcsr(person.getChkstr());
		page.verifyPassword();
		
		if(!page.isPass()){
			new SessionMPR(session).setMessage(page.getMessage());
			return "redirect:password";
		}
		
		person.setChkstr(page.getConfirmcsr());
		personRepository.save(person);
		sessionUtil.invalidate();
		map.put(Constant.MESSAGE_NAME,"密码修改成功！");
		return "password/sucess";	
	}
	
	

}
