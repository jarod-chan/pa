package cn.fyg.pa.mail;

import org.junit.Test;

public class TestMail {
	
	@Test
	public void sendByFygmail(){
		String subject="重置邮件";
		String context="内容";
		String to="jarod_chan@qq.com";
		Mail mail=new Mail(subject,context,to);
		
//		java.util.List<String> list=new java.util.ArrayList<String>();
//		list.add("jarod_chan@qq.com");
//		list.add("chen.z.w@hotmail.com");		
//		Mail mail=new Mail(subject,context,list);
		

		Sender sender=SenderFactory.createSender();
		sender.sendMail(mail);
	}

}
