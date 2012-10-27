package cn.fyg.pa.interfaces.module.questionaires.login;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.questionaires.KeyService;
import cn.fyg.pa.application.questionaires.QuesService;
import cn.fyg.pa.domain.model.questionaires.key.Key;
import cn.fyg.pa.domain.model.questionaires.ques.Ques;
import cn.fyg.pa.domain.model.questionaires.ques.QuesState;
import cn.fyg.pa.interfaces.module.questionaires.QsConstant;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;

@Controller
@RequestMapping("/qs/login")
public class QsloginCtl {
	
	@Resource
	KeyService keyService;
	@Resource
	QuesService quesService;
	@Resource
	SessionUtil sessionUtil;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toLogin(Map<String,Object> map,HttpSession session){
		map.put("message",new SessionMPR(session).getMessage());
		return "questionaires/login";
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String login(KeyBean keyBean,Map<String,Object> map,HttpSession session){
		boolean result=keyService.checkKey(keyBean.getKey());
		if(result){
			Key key = keyService.find(keyBean.getKey());
			Ques ques=quesService.find(key.getQtid());
			sessionUtil.setValue(QsConstant.UUID, keyBean.getKey());
			sessionUtil.setValue(QsConstant.QTID, ques.getId());
			return ques.getState()==QuesState.active?"redirect:beg":"redirect:close";
		}
		map.put("message",String.format("您输入的认证码:[%s]没有通过校验，请核对以后重新输入！", keyBean.getKey()));
		map.put("keyBean", keyBean);
		return "questionaires/login";
	}

}
