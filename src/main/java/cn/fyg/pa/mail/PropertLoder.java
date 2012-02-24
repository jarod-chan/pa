package cn.fyg.pa.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertLoder {
	
	public static final Logger logger=LoggerFactory.getLogger(PropertLoder.class);
	
	public static Properties loadProperties(String resString) {
		Properties prop=new Properties();
		InputStream in = Object.class.getResourceAsStream(resString);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读取邮件配置文件错误", e);
		}
		return prop;
	}

}
