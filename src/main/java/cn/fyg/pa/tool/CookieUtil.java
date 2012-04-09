package cn.fyg.pa.tool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.fyg.pa.domain.model.Person;


public class CookieUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	
	public static void setChkstrCookie(HttpServletRequest request,
			HttpServletResponse response, String chkstr) {
		Cookie[] cookies=request.getCookies(); 
		try 
		{ 
			int len=(cookies==null?0:cookies.length);
		     for(int i=0;i<len;i++)   
		     { 
		      Cookie cookie = new Cookie(Constant.COOKIE_FLAG,null); 
		      cookie.setMaxAge(0); 
		      response.addCookie(cookie); 
		     } 
		}catch(Exception ex) 
		{ 
			logger.error("清空cookie出错", ex);
		} 
		Cookie cookie = new Cookie(Constant.COOKIE_FLAG,chkstr);
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
	}
	
	public static boolean checkLogin(String cookieChkstr, Person currPerson) {
		if(currPerson==null) return false;
		if(!currPerson.getChkstr().equals(cookieChkstr)) return false;
		return true;
	}

}
