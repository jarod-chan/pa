package cn.fyg.pa.mail;

import org.junit.Test;

public class TestMail {
	
	@Test
	public void sendByFygmail(){
		String subject="重置邮件";
//		String context="内容";
		
		String context="你好！<br>"+
				"你的考核系统登录信息如下<br>" +
				"用户名："+"dddddfffff"+"<br>"+
				"密码："+"xxxx"+"<br>"+
				"点击以下链接登录考核系统<a href='http://kh.fyg.cn:8080/pa'>方远考核系统</a><br>";
		
		String to="jarod_chan@qq.com";
		Mail mail=new Mail(subject,context,to);
		
//		java.util.List<String> list=new java.util.ArrayList<String>();
//		list.add("jarod_chan@qq.com");
//		list.add("chen.z.w@hotmail.com");		
//		Mail mail=new Mail(subject,context,list);
		

		Sender sender=SenderFactory.createSender();
//		sender.sendMail(mail);
	}

}
