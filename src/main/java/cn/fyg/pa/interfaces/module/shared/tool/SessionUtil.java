package cn.fyg.pa.interfaces.module.shared.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	private  HttpSession session = null;

	public SessionUtil(HttpServletRequest request){
		session = request.getSession();      
	}
	
	public SessionUtil(HttpSession session){
		this.session=session;
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
	
	public void remove(String key){
		session.removeAttribute(key);
	}
}
