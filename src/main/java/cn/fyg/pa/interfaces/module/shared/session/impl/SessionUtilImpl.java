package cn.fyg.pa.interfaces.module.shared.session.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;
import cn.fyg.pa.interfaces.module.shared.tool.Tool;

/**
 * session 处理
 */
@Component
public class SessionUtilImpl implements SessionUtil {
	
	private static final String PA_TOKEN = "pa-token";
	@Resource
	HttpSession httpSession;
	
	@Override
	public void setValue(String key,Object value){
		httpSession.setAttribute(key, value);
	}
	
	@Override
	public <T> T getValue(String key){
		Object obj=httpSession.getAttribute(key);
		if(obj==null) return null;
		@SuppressWarnings("unchecked")
		T returnVal = (T)obj;
		return returnVal;
	}
	
	@Override
	public void invalidate(){
		httpSession.invalidate();
	}

	@Override
	public String createToken() {
		String token = Tool.getPassword(6L);
		httpSession.setAttribute(PA_TOKEN, token);
		return token;
	}

	@Override
	public boolean checkToken(String token) {
		if(token==null) return false;
		Object obj=httpSession.getAttribute(PA_TOKEN);
		httpSession.setAttribute(PA_TOKEN, null);
		if(obj==null) return false;
		String saveToken=obj.toString();
		return saveToken.equals(token);
	}
	
}
