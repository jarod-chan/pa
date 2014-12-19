package cn.fyg.pa.interfaces.web.module.system.password;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.infrastructure.mail.EmailException;
import cn.fyg.pa.infrastructure.mail.EmailService;

@Component
public class FetchPasswordFacade {
	
	@Resource
	EmailService emailService;

	public void sendPasswordToPerson(Person person) throws EmailException {
		String to=person.getEmail();
		String subject = "方远考核系统-密码取回邮件";
		String htmlText=createContextByPerson(person);
		emailService.sendMail(to, subject, htmlText);
	}
	
	private String createContextByPerson(Person person) {
		String context = person.getName()+ "，你好！<br/>"
				+ "你的考核系统登录信息如下<br/>"
				+ "用&nbsp;户："+ person.getName() + "<br/>"
				+ "密&nbsp;码："+ person.getChkstr() + "<br/>"
				+ "点击以下链接登录考核系统<a href='http://kh.fyg.cn:8080/pa'>方远考核系统</a><br/>";
		return context;
	}

}
