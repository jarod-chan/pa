package cn.fyg.pa.interfaces.module.questionaires.part;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.pa.application.questionaires.ChoiceAnswerService;
import cn.fyg.pa.application.questionaires.ShortAnswerService;
import cn.fyg.pa.domain.model.questionaires.answer.UuidAnswer;
import cn.fyg.pa.domain.model.questionaires.answer.UuidShort;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.choice.ReceiveBean;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.choice.ReceivePage;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.shortanswer.ShortBean;
import cn.fyg.pa.interfaces.module.questionaires.part.dao.shortanswer.ShortPage;

@Component
public class PartFacade {
	
	@Autowired
	ChoiceAnswerService choiceAnswerService;
	@Resource
	ShortAnswerService shortAnswerService;
	
	public void saveChoice(ReceivePage receivePage,String uuid,Long qtid,Long partid){
		List<UuidAnswer> uuidAnswers=new ArrayList<UuidAnswer>();
		for (ReceiveBean receiveBean : receivePage.getReceiveBeans()) {
			UuidAnswer uuidAnswer = receiveBean.getId() == null ? 
					choiceAnswerService.create(uuid, qtid, partid) : 
					choiceAnswerService.find(receiveBean.getId());
			uuidAnswer.setValue(receiveBean.getValue());
			uuidAnswers.add(uuidAnswer);
		}
		choiceAnswerService.save(uuidAnswers);
	}

	public void saveAnswer(ShortPage shortPage, String uuid, Long qtid,Long partid) {
		List<UuidShort> uuidShorts=new ArrayList<UuidShort>();
		for (ShortBean shortBean : shortPage.getShortBeans()) {
			UuidShort uuidShort=shortBean.getId()==null?
					shortAnswerService.create(uuid, qtid, partid):
					shortAnswerService.find(shortBean.getId());
			uuidShort.setProblemid(shortBean.getProblemid());
			uuidShort.setValue(shortBean.getValue());
			uuidShorts.add(uuidShort);
		}
		shortAnswerService.save(uuidShorts);
	}

}
