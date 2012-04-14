package cn.fyg.pa.infrastructure.message.imp;

import javax.servlet.http.HttpSession;

import cn.fyg.pa.infrastructure.message.MessagePasser;
import cn.fyg.pa.interfaces.tool.Constant;

public class SessionMPR implements MessagePasser {
	
	private HttpSession session;
	
	public SessionMPR(HttpSession session){
		this.session=session;
	}

	@Override
	public void setMessage(String message) {
		session.setAttribute(Constant.MESSAGE_NAME, message);
	}

	@Override
	public String getMessage() {
		Object obj=session.getAttribute(Constant.MESSAGE_NAME);
		session.setAttribute(Constant.MESSAGE_NAME, null);
		return obj==null?null:(String) obj;
	}

}
