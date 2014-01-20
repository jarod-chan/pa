package cn.fyg.pa.infrastructure.mail;

public interface EmailService {

	void sendMail(String to, String subject, String htmlText)
			throws EmailException;

}
