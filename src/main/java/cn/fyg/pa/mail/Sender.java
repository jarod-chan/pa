package cn.fyg.pa.mail;

import java.security.Security;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender {

	protected int sendMail(Mail mail){
		StmpServerProps p = new StmpServerProps();
		return sendMail( mail, p);
	}

	protected int sendMail(Mail mail, StmpServerProps p) {
		//@SuppressWarnings("restriction")
		com.sun.net.ssl.internal.ssl.Provider provider = new com.sun.net.ssl.internal.ssl.Provider();
		Security.addProvider(provider);
		int result = 0;
		try {
			final String username = p.getUsername();
			final String password = p.getPassword();

			Session session = Session.getDefaultInstance(p.getProperties(),
					new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			String[] to = mail.getTo();
			for (int i = 0, size = to.length; i < size; i++) {
				InternetAddress toaddress = new InternetAddress(to[i]);
				msg.addRecipient(RecipientType.TO, toaddress);
			}
			msg.setSubject((mail.isMarkSubject() ? mail.markSubject : "")
					+ mail.getSubject());
			msg.setText(mail.getContext()
					+ (mail.isDefaultSign() ? mail.defaultSign
							: mail.defaultSepatate + mail.getSign()));
			msg.setSentDate(mail.getDate());
			Transport.send(msg);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
