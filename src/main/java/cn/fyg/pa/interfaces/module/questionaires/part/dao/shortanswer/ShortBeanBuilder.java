package cn.fyg.pa.interfaces.module.questionaires.part.dao.shortanswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.questionaires.answer.UuidShort;
import cn.fyg.pa.domain.model.questionaires.problem.ShortAnswer;

public class ShortBeanBuilder {
	
	public static List<ShortBean> create(List<ShortAnswer> shortAnswers,List<UuidShort> uuidShorts){
		List<ShortBean> shortBeans=new ArrayList<ShortBean>(); 
		Map<Long,UuidShort> uuidShortMap=new HashMap<Long,UuidShort>();
		for (UuidShort uuidShort : uuidShorts) {
			uuidShortMap.put(uuidShort.getProblemid(),uuidShort);
		}
		for (ShortAnswer shortAnswer : shortAnswers) {			
			Long problemid = shortAnswer.getId();
			ShortBean shortBean=new ShortBean();
			shortBean.setShortAnswer(shortAnswer);
			shortBean.setProblemid(problemid);
			UuidShort uuidShort=uuidShortMap.get(problemid);
			if(uuidShort!=null){
				shortBean.setId(uuidShort.getId());
				shortBean.setValue(uuidShort.getValue());
			}
			shortBeans.add(shortBean);
		}
		return shortBeans;
	}

}
