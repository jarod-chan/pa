package cn.fyg.pa.infrastructure.mail;

import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Mail {
	//资源文件
	private static final String resStr="/mail/mailsetting.properties";
	private static Properties prop;
	
	private String subject; //主题
	private String context; //内容
	private String from;    //发件人
	private String[] to;    //收件人
	//系统自动生成
	private Date date;		 //邮件时间,系统自动生成
	private String sign;	 //邮件签名
	private boolean isMarkSubject;//是否启用主题标识
	private boolean isDefaultSign;//是否启用邮件签名
	private static final String markSubject; //邮件主题
	private static final String defaultSepatate; //内容和签名分隔符
	private static final String defaultSign; // 默认签名
	
	static {
		prop = PropertLoder.loadProperties(Mail.class,resStr);
		markSubject=prop.getProperty("markSubject");
		defaultSepatate="<br>"+prop.getProperty("defaultSepatate")+"<br>";
		defaultSign=defaultSepatate+prop.getProperty("defaultSign");
	}

	
	public void setMarkSubject(boolean isMarkSubject) {
		this.isMarkSubject = isMarkSubject;
	}

	public void setDefaultSign(boolean isDefaultSign) {
		this.isDefaultSign = isDefaultSign;
	}
	
	public Mail(String subject, String context,String to){
		this.init(subject, context,null,to==null?null:new String[]{to});
	}

	public Mail(String subject, String context,List<String> to){
		this.init(subject, context,null,to==null?null:(String[])to.toArray(new String[to.size()]));
	}
	
	public Mail(String subject, String context, String from, List<String> to){
		this.init(subject, context,from,to==null?null:(String[])to.toArray(new String[to.size()]));
	}
	
	private void init(String subject, String context, String from, String[] to) {
		this.subject = subject;
		this.context = context;
		this.from = from;
		this.to = to;
		this.date=new java.util.Date();
		this.sign="";

		this.isMarkSubject=Boolean.valueOf(prop.getProperty("isMarkSubject")).booleanValue();
		this.isDefaultSign=Boolean.valueOf(prop.getProperty("isDefaultSign")).booleanValue();
	}
	
	public boolean isMarkSubject() {
		return isMarkSubject;
	}

	public boolean isDefaultSign() {
		return isDefaultSign;
	}

	public String getSubject() {
		return subject+"--"+(isMarkSubject?markSubject:"");
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public Date getDate() {
		return date;
	}
	public String getSign() {
		return (isDefaultSign?defaultSign:sign);
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}