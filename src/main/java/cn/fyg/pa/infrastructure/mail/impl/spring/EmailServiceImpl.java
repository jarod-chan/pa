package cn.fyg.pa.infrastructure.mail.impl.spring;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import cn.fyg.pa.infrastructure.mail.EmailException;
import cn.fyg.pa.infrastructure.mail.EmailService;



@Component
public class EmailServiceImpl implements EmailService {
	
	@Resource
	private JavaMailSender javaMailSender;
	@Resource
	private String systemEmail;
	
	

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}



	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}



	public String getSystemEmail() {
		return systemEmail;
	}



	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}



	@Override
	public void sendMail(String to, String subject, String htmlText)
			throws EmailException {
		MimeMessage mineMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mineMessage);
		try {
			helper.setFrom(systemEmail);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlText, true);
			
			javaMailSender.send(mineMessage);
		} catch (Exception e) {
			throw new EmailException("Faild to send mail.",e);
		}
	}

}
