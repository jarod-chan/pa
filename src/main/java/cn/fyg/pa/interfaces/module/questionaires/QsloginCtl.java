package cn.fyg.pa.interfaces.module.questionaires;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.questionaires.KeyService;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;

@Controller
@RequestMapping("/qs/login")
public class QsloginCtl {
	
	@Resource
	KeyService keyService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toLogin(Map<String,Object> map,HttpSession session){
		map.put("message",new SessionMPR(session).getMessage());
		return "questionaires/login";
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String login(KeyBean keyBean,HttpSession session){
		boolean result=keyService.checkKey(keyBean.getKey());
		if(result){
			return "";
		}
		new SessionMPR(session).setMessage(String.format("您输入的认证码:[%s]没有通过校验，请核对以后重新输入！", keyBean.getKey()));
		return "redirect:login";
	}

}
