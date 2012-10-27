package cn.fyg.pa.interfaces.module.questionaires.part;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.questionaires.ChoiceService;
import cn.fyg.pa.application.questionaires.KeyService;
import cn.fyg.pa.application.questionaires.PartService;
import cn.fyg.pa.application.questionaires.ChoiceAnswerService;
import cn.fyg.pa.application.questionaires.ShortAnswerService;
import cn.fyg.pa.application.questionaires.ShortService;
import cn.fyg.pa.domain.model.questionaires.answer.UuidAnswer;
import cn.fyg.pa.domain.model.questionaires.answer.UuidShort;
import cn.fyg.pa.domain.model.questionaires.part.Part;
import cn.fyg.pa.domain.model.questionaires.part.PartEnum;
import cn.fyg.pa.domain.model.questionaires.problem.Choice;
import cn.fyg.pa.domain.model.questionaires.problem.ShortAnswer;
import cn.fyg.pa.interfaces.module.questionaires.QsConstant;
import cn.fyg.pa.interfaces.module.questionaires.common.Filter;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.choice.PartBean;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.choice.PartBeanBuiler;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.choice.ReceivePage;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.shortanswer.ShortBean;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.shortanswer.ShortBeanBuilder;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.shortanswer.ShortPage;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;

@Controller
@RequestMapping("/qs/part")
public class PartCtl {
	
	@Resource
	SessionUtil sessionUtil;
	@Resource
	ChoiceService choiceService;
	@Resource
	ChoiceAnswerService choiceAnswerService;
	@Resource
	PartService partService;
	@Resource
	PartFacade partFacade;
	@Resource
	ShortService shortService;
	@Resource
	ShortAnswerService shortAnswerService;
	@Resource
	KeyService keyService;
	
	@RequestMapping(value="/{partid}",method=RequestMethod.GET)
	public String toPart(@PathVariable("partid")Long partid,Map<String,Object> map,HttpSession session){
		if(!Filter.isLogin(sessionUtil)){
			return Filter.reLongin(session);
		}
		Long qtid=sessionUtil.getValue(QsConstant.QTID);
		String uuid=sessionUtil.getValue(QsConstant.UUID);
		map.put("message",new SessionMPR(session).getMessage());
		List<Part> parts = partService.findPrevNext(qtid, partid);
		map.put("prev", parts.get(0));
		map.put("part", parts.get(1));
		map.put("next", parts.get(2));
		if(parts.get(1).getType()==PartEnum.choice){
			return choice(uuid, qtid, partid, map);
		}
		return shortAnswer(uuid, qtid, partid, map);
	}

	//选择题页面
	public String choice(String uuid, Long qtid, Long partid,Map<String, Object> map) {
		List<Choice> choiceList = choiceService.findPartChoice(qtid, partid);
		List<UuidAnswer> uuidAnswerList = choiceAnswerService.findAnswer(uuid, qtid, partid);
		List<PartBean> partBeanList = PartBeanBuiler.createPartBean(choiceList, uuidAnswerList);
		map.put("partBeanList", partBeanList);
		return "questionaires/part-choice";
	}
	
	//问答题页面
	public String shortAnswer(String uuid,Long qtid,Long partid, Map<String, Object> map){
		List<ShortAnswer> shortAnswers = shortService.findPartShort(qtid, partid);
		List<UuidShort> uuidShorts = shortAnswerService.findUuidShort(uuid, qtid, partid);
		List<ShortBean> shortBeanList = ShortBeanBuilder.create(shortAnswers, uuidShorts);
		map.put("shortBeanList", shortBeanList);
		return "questionaires/part-answer";
	}
	
	@RequestMapping(value="/{partid}/next_choice",method=RequestMethod.POST)
	public String saveChoice(ReceivePage receivePage,@PathVariable("partid")Long partid,Map<String,Object> map,HttpSession session){
		if(!Filter.isLogin(sessionUtil)){
			return Filter.reLongin(session);
		}
		Long qtid=sessionUtil.getValue(QsConstant.QTID);
		String uuid=sessionUtil.getValue(QsConstant.UUID);
		partFacade.saveChoice(receivePage, uuid, qtid, partid);
		if(receivePage.isFinish()){
			keyService.finish(uuid);
			return Filter.finish();
		}
		return String.format("redirect:../%s",partid.intValue()+1);
	}
	
	@RequestMapping(value="/{partid}/next_answer",method=RequestMethod.POST)
	public String saveAnswer(ShortPage shortPage,@PathVariable("partid")Long partid,Map<String,Object> map,HttpSession session){
		if(!Filter.isLogin(sessionUtil)){
			return Filter.reLongin(session);
		}
		Long qtid=sessionUtil.getValue(QsConstant.QTID);
		String uuid=sessionUtil.getValue(QsConstant.UUID);
		partFacade.saveAnswer(shortPage,uuid,qtid,partid);
		if(shortPage.isFinish()){
			keyService.finish(uuid);
			return Filter.finish();
		}
		return String.format("redirect:../%s",partid.intValue()+1);
	}
}
