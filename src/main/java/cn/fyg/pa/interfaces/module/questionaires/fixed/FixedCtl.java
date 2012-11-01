package cn.fyg.pa.interfaces.module.questionaires.fixed;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.questionaires.KeyService;
import cn.fyg.pa.application.questionaires.PartService;
import cn.fyg.pa.application.questionaires.QuesService;
import cn.fyg.pa.domain.model.questionaires.key.Key;
import cn.fyg.pa.domain.model.questionaires.part.Part;
import cn.fyg.pa.domain.model.questionaires.ques.Ques;
import cn.fyg.pa.interfaces.module.questionaires.QsConstant;
import cn.fyg.pa.interfaces.module.questionaires.common.Filter;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;

@Controller
public class FixedCtl {
	
	@Resource
	QuesService quesService;
	@Resource
	KeyService keyService;
	@Resource
	PartService partService;
	@Resource
	SessionUtil sessionUtil;
	
	@RequestMapping(value="/qs/beg",method=RequestMethod.GET)
	public String toBeg(Map<String,Object> map,HttpSession session){
		if(!Filter.isLogin(sessionUtil)){
			return Filter.reLongin(session);
		}
		String uuid=sessionUtil.getValue(QsConstant.UUID);
		Long qtid=sessionUtil.getValue(QsConstant.QTID);
		Key key = keyService.find(uuid);
		Ques ques = quesService.find(qtid);
		map.put("key", key);
		map.put("ques", ques);
		map.put("message",new SessionMPR(session).getMessage());
		return "questionaires/beg";
	}
	
	@RequestMapping(value="/qs/end",method=RequestMethod.GET)
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
	
	@RequestMapping(value="/qs/close",method=RequestMethod.GET)
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
	

	@RequestMapping(value="/qs/start",method=RequestMethod.POST)
	public String start(Map<String,Object> map,HttpSession session){
		if(!Filter.isLogin(sessionUtil)){
			return Filter.reLongin(session);
		}
		Long qtid=sessionUtil.getValue(QsConstant.QTID);
		String uuid=sessionUtil.getValue(QsConstant.UUID);
		keyService.used(uuid);
		Part part = partService.findFirstPart(qtid);
		return String.format("redirect:/qs/part/%s",part.getId());
	}
	
	@RequestMapping(value="/qs/logout",method=RequestMethod.POST)
	public String logout(Map<String,Object> map,HttpSession session){
		sessionUtil.invalidate();
		return "redirect:/first";
	}

}
