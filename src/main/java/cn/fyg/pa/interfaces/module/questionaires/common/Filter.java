package cn.fyg.pa.interfaces.module.questionaires.common;


import javax.servlet.http.HttpSession;

import cn.fyg.pa.interfaces.module.questionaires.QsConstant;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;

public class Filter {
	
	public static boolean isLogin(SessionUtil sessionUtil){
		if(sessionUtil==null) return false;
		Long uuid=sessionUtil.getValue(QsConstant.UUID);
		Long qtid=sessionUtil.getValue(QsConstant.QTID);
		return (uuid!=null)&&(qtid!=null);
	}
	
	public static String reLongin(HttpSession session){
		new SessionMPR(session).setMessage("由于长时间未操作，请重新登录系统");
		return "redirect:login";
	}

}
