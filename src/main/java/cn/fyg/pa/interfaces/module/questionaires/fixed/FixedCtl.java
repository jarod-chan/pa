package cn.fyg.pa.interfaces.module.questionaires.fixed;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.questionaires.QuesService;
import cn.fyg.pa.domain.model.questionaires.ques.Ques;
import cn.fyg.pa.interfaces.module.questionaires.QsConstant;
import cn.fyg.pa.interfaces.module.questionaires.common.Filter;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;

@Controller
@RequestMapping("/qs/fixed")
public class FixedCtl {
	
	@Resource
	QuesService quesService;
	@Resource
	SessionUtil sessionUtil;
	
	@RequestMapping(value="/start",method=RequestMethod.GET)
	public String toStart(Map<String,Object> map,HttpSession session){
		if(!Filter.isLogin(sessionUtil)){
			return Filter.reLongin(session);
		}
		map.put("message",new SessionMPR(session).getMessage());
		return "questionaires/start";
	}
	
	@RequestMapping(value="/end",method=RequestMethod.GET)
	public String toEnd(Map<String,Object> map,HttpSession session){
		if(!Filter.isLogin(sessionUtil)){
			return Filter.reLongin(session);
		}
		Long qtid=sessionUtil.getValue(QsConstant.QTID);
		Ques ques = quesService.find(qtid);
		map.put("ques", ques);
		map.put("message",new SessionMPR(session).getMessage());
		return "questionaires/end";
	}
	
	@RequestMapping(value="/close",method=RequestMethod.GET)
	public String toClose(Map<String,Object> map,HttpSession session){
		if(!Filter.isLogin(sessionUtil)){
			return Filter.reLongin(session);
		}
		Long qtid=sessionUtil.getValue(QsConstant.QTID);
		Ques ques = quesService.find(qtid);
		map.put("ques", ques);
		map.put("message",new SessionMPR(session).getMessage());
		return "questionaires/close";
	}
	

}
