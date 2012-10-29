package cn.fyg.pa.interfaces.module.questionaires.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.fyg.pa.application.questionaires.QuesService;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;

@Controller
@RequestMapping("/admin/ques")
public class QuesAdminCtl {
	
	@Resource
	QuesAdminFacade adminFacade;
	@Resource
	QuesService quesService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toAdmin(Map<String,Object> map,HttpSession session){
		List<QuesBean> quesBeanList = adminFacade.getQuesBeanList();
		map.put("quesBeanList", quesBeanList);
		map.put("message", new SessionMPR(session).getMessage());
		return "questionaires/admin";
	}
	
	
	@RequestMapping(value="/produceKey",method=RequestMethod.POST)
	public String produceKey(@RequestParam("qtid")Long qtid,@RequestParam("keyNum")int keyNum,HttpSession session){
		adminFacade.produceKey(qtid, keyNum);
		new SessionMPR(session).setMessage(String.format("%s个认证码生成完成",keyNum));
		return "redirect:../ques";
	}
	
	@RequestMapping(value="/recover",method=RequestMethod.POST)
	public String recover(@RequestParam("keystr")String keystr,HttpSession session){
		try{
			adminFacade.recover(keystr);
		}catch(Exception e){
			new SessionMPR(session).setMessage(e.getMessage());
			return "redirect:../ques";
		}
		
		new SessionMPR(session).setMessage(String.format("验证码：%s回收成功！", keystr));
		return "redirect:../ques";
	}
	
	@RequestMapping(value="/finish",method=RequestMethod.POST)
	public String finish(@RequestParam("qtid")Long qtid,HttpSession session){
		quesService.finish(qtid);
		new SessionMPR(session).setMessage("调查已经被终止");
		return "redirect:../ques";
	}
}
