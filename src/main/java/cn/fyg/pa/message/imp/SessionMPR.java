package cn.fyg.pa.message.imp;

import javax.servlet.http.HttpSession;

import cn.fyg.pa.message.MessagePasser;
import cn.fyg.pa.tool.Constant;

public class SessionMPR implements MessagePasser {
	
	private HttpSession session;
	
	public SessionMPR(HttpSession session){
		this.session=session;
	}

	@Override
	public void setMessage(String message) {
		session.setAttribute(Constant.COOKIE_MSG, message);
	}

	@Override
	public String getMessage() {
		Object obj=session.getAttribute(Constant.COOKIE_MSG);
		session.setAttribute(Constant.COOKIE_MSG, null);
		return obj==null?null:(String) obj;
	}

}
