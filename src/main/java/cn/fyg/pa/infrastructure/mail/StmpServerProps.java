package cn.fyg.pa.infrastructure.mail;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class StmpServerProps {
	
	public static final Logger logger=LoggerFactory.getLogger(StmpServerProps.class);
	
	//资源文件
	private static final String resStr="/mail/stmpserver.properties";
	
	private String username; //邮件账户
	private String password;		//邮件密码
	private String host;		//stmp服务器
	private String port;				//stmp端口
					
	//固定设置
	private String auth = "true";
	private String fallback="false";
	private String sslfactory="javax.net.ssl.SSLSocketFactory";
	
	public StmpServerProps(){
		Properties prop = PropertLoder.loadProperties(StmpServerProps.class,resStr);
		this.username=prop.getProperty("username");
		this.password=prop.getProperty("password");
		this.host=prop.getProperty("host");
		this.port=prop.getProperty("port");
	}	
	
	public StmpServerProps(String username, String password, String host,String port) {
		this.host = host;
		this.password = password;
		this.port = port;
		this.username = username;
	}


	public Properties getProperties(){
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.socketFactory.fallback", fallback);
		props.put("mail.smtp.socketFactory.class",sslfactory);
		return props;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
