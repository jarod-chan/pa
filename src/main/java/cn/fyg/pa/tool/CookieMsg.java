package cn.fyg.pa.tool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO 用cookies来传递message   这个有很多问题  暂时保留不使用
public class CookieMsg {
	
	private static final Logger logger = LoggerFactory.getLogger(CookieMsg.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CookieMsg(HttpServletRequest request,HttpServletResponse response){
		this.request=request;
		this.response=response;
	}
	
	private Cookie setCookieVal(String message) {
		Cookie msgCookie = null;
		Cookie[] cookies = request.getCookies();
		int len = (cookies == null ? 0 : cookies.length);
		for (int i = 0; i < len; i++) {
			if (cookies[i].getName().equalsIgnoreCase(Constant.COOKIE_MSG)) {
				msgCookie = cookies[i];
				msgCookie.setValue(message);
			}
		}
		if(msgCookie==null){
			msgCookie= new Cookie(Constant.COOKIE_MSG,message);
			msgCookie.setMaxAge(-1);
		}
		return msgCookie;
	}
	
	private String getCookieVal() {
		Cookie[] cookies = request.getCookies();
		int len = (cookies == null ? 0 : cookies.length);
		for (int i = 0; i < len; i++) {
			if (cookies[i].getName().equalsIgnoreCase(Constant.COOKIE_MSG)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}
	
	public void setMsgCookie(String message) {
		logger.info("记录消息cookie");
		Cookie msgCookie=setCookieVal(message);
//		try {
//			message=URLEncoder.encode(message,"utf8") ;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		response.addCookie(msgCookie);
	}
	
	
	public String getMsgCookie(){
		logger.info("获得并清空消息cookie");
		
		String message=getCookieVal();
		
		Cookie msgCookie=setCookieVal(null);
		response.addCookie(msgCookie);
		
		return message;
	}

}
