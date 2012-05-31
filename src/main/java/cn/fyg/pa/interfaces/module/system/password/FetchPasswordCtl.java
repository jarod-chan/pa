package cn.fyg.pa.interfaces.module.system.password;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.infrastructure.mail.EmailException;
import cn.fyg.pa.infrastructure.mail.EmailService;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/fetchcsr")
public class FetchPasswordCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(FetchPasswordCtl.class);
	

	@Resource
	PersonRepository personRepository;

	@Resource
	EmailService emailService;
	
	@Resource
	FetchPasswordFacade fetchPasswordFacade;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String show(){
		logger.info("show");
		return "fetchcsr/fetchpassword";
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)  
	public String fetchPassword(UsernameBean usernameBean,Map<String,Object> map){
		logger.info("fetchPassword");
		Person person=personRepository.findByName(usernameBean.getUsername().trim());
		map.put("usernameBean", usernameBean);
		if(person==null){
			map.put(Constant.MESSAGE_NAME, "该用户不存在,请重新输入！");
			return "fetchcsr/fetchpassword";
		}
		if(person!=null&&StringUtils.isBlank(person.getEmail())){
			map.put(Constant.MESSAGE_NAME, "该用户没设置个人邮箱！");
			return "fetchcsr/fetchpassword";
		}
		
		try {
			fetchPasswordFacade.sendPasswordToPerson(person);
		} catch (EmailException e) {
			logger.error("",e);
			map.put(Constant.MESSAGE_NAME, "邮件服务器发送错误，请联系统管理人员！");
			return "fetchcsr/fetchpassword";
		}
		map.put("username",person.getName());
		map.put("email", person.getEmail());
		return "fetchcsr/mailsucess";
	}
	
}
