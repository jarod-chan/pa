package cn.fyg.pa.interfaces.module.questionaires.part.dao.choice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.questionaires.answer.UuidAnswer;
import cn.fyg.pa.domain.model.questionaires.problem.Choice;

public class PartBeanBuiler {
	
	public static List<PartBean> createPartBean(List<Choice> choiceList,List<UuidAnswer> uuidAnswerList){
		List<PartBean> partBeans=new ArrayList<PartBean>();
		Map<Long,Long> answerMap=new HashMap<Long,Long>();
		for (UuidAnswer uuidAnswer : uuidAnswerList) {
			answerMap.put(uuidAnswer.getValue(), uuidAnswer.getId());
		}
		for (Choice choice : choiceList) {
			PartBean partBean=new PartBean();
			partBean.setChoice(choice);
			partBean.groupAnswerByType();
			partBean.initUuidAnswer(answerMap);
			partBeans.add(partBean);
		}
		return partBeans;
	}

}
