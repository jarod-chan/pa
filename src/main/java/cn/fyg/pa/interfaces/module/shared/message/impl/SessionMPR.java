package cn.fyg.pa.interfaces.module.shared.message.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Component
public class SessionMPR implements MessagePasser {
	
	private static final String MESSAGE_NAME=Constant.MESSAGE_NAME;
	
	@Resource
	HttpSession session;
	
	public SessionMPR(){
		
	}
	
	public SessionMPR(HttpSession session){
		this.session=session;
	}

	@Override
	public void setMessage(String message) {
		session.setAttribute(MESSAGE_NAME, message);
	}

	@Override
	public String getMessage() {
		Object obj=session.getAttribute(MESSAGE_NAME);
		session.setAttribute(MESSAGE_NAME, null);
		return obj==null?null:(String) obj;
	}

}
