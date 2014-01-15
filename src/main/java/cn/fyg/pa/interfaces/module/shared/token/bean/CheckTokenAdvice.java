package cn.fyg.pa.interfaces.module.shared.token.bean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;
import cn.fyg.pa.interfaces.module.shared.token.annotation.CheckToken;

@Component
public class CheckTokenAdvice {
	
	@Resource  
	private HttpServletRequest request;  
	@Resource
	SessionUtil sessionUtil; 
	@Resource
	MessagePasser messagePasser;
	
	public String around(ProceedingJoinPoint pjp,CheckToken checkToken) throws Throwable{
		Object object = request.getParameter("session_token");
		String token=object!=null? object.toString():null;
		if(!sessionUtil.checkToken(token)){
			messagePasser.setMessage("重复提交数据导致数据重复，请重新填写。（请不要使用浏览器后退功能！）");
			return checkToken.redirectUrl();
		}
		Object proceed = pjp.proceed();
		return proceed.toString();
	}

}
