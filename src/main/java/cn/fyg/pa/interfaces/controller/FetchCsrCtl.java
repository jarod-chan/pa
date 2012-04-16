package cn.fyg.pa.interfaces.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.infrastructure.mail.Mail;
import cn.fyg.pa.infrastructure.mail.Sender;
import cn.fyg.pa.infrastructure.perisistence.PersonDao;
import cn.fyg.pa.interfaces.page.SendMailPage;

@Controller
@RequestMapping("/fetchcsr")
public class FetchCsrCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(FetchCsrCtl.class);
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private Sender sender;
	
	@RequestMapping(value="")
	public ModelAndView show(){
		logger.info("show");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("fetchcsr/mail");
		return mav;
	}
	
	 @RequestMapping(value="",method=RequestMethod.POST)  
	public ModelAndView sendMail(SendMailPage sendMailPage){
		logger.info("sendMail");
		if(!sendMailPage.checkPage()){
			return getFailMav("该用户不存在,请重新输入！");
		}
		
		Person person=personDao.getMailAdressByUsername(sendMailPage.getUsername());
		if(person==null){
			return getFailMav("该用户不存在,请重新输入！");
		}
		if(person!=null&&StringUtils.isBlank(person.getEmail())){
			return getFailMav("该用户没设置个人邮箱！");
		}
		
		Mail mail = createEmailByPerson(person);
		
		sender.sendMail(mail);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("fetchcsr/sucess");
		mav.addObject("username",sendMailPage.getUsername());
		mav.addObject("email", person.getEmail());
		return mav;
		
	}

	private Mail createEmailByPerson(Person person) {
		String subject = "密码取回邮件";
		String context = person.getName()+ "，你好！<br>"
				+ "你的考核系统登录信息如下<br>"
				+ "用户名："+ person.getName() + "<br>"
				+ "密码："+ person.getChkstr() + "<br>"
				+ "点击以下链接登录考核系统<a href='http://kh.fyg.cn:8080/pa'>方远考核系统</a><br>";
		String to = person.getEmail();
		Mail mail = new Mail(subject, context, to);
		return mail;
	}

	private ModelAndView getFailMav(String message) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("fetchcsr/mail");
		mav.addObject("msg",message);
		return mav;
	}

}
