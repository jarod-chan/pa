package cn.fyg.pa.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;

public class TestProperties {
	
	@Test
	public void testProp(){
		Properties prop=new Properties();
		InputStream in = Object.class.getResourceAsStream("/mail/mailsetting.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!prop.isEmpty()) {
			Enumeration<?> enum1 = prop.propertyNames();
			while (enum1.hasMoreElements()) {
				String strKey = (String) enum1.nextElement();
				String strValue = prop.getProperty(strKey);
				System.out.println(strKey + "=" + strValue);
			}
		}
	}

}
