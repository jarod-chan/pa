package cn.fyg.pa.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	private  HttpSession session = null;

	public SessionUtil(HttpServletRequest request){
		session = request.getSession();      
	}
	
	public void setValue(String key,Object value){
		session.setAttribute(key, value);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(String key, Class<T> resultClass){
		Object obj=session.getAttribute(key);
		if(obj==null) return null;
		return (T)obj;
	}
}
