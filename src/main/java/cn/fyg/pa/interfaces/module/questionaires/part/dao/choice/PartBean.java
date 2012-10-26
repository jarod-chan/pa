package cn.fyg.pa.interfaces.module.questionaires.part.dao.choice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.questionaires.problem.Answer;
import cn.fyg.pa.domain.model.questionaires.problem.Choice;

public class PartBean {
	
	private Choice choice;
	
	private List<PartItem> partItems;
	
	public void groupAnswerByType(){
		this.partItems=new ArrayList<PartItem>();
		int prevType=0;
		for (Answer answer: this.choice.getAnswers()) {
			if(prevType!=answer.getType()){
				prevType=answer.getType();
				PartItem partItem=new PartItem();
				partItem.setAnswerList(new ArrayList<Answer>());
				partItem.setType(prevType);
				this.partItems.add(partItem);
			}
			this.partItems.get(this.partItems.size()-1).getAnswerList().add(answer);
		}
	}
	
	public void initUuidAnswer(Map<Long, Long> answerMap) {
		for (PartItem partItem : this.partItems) {
			for (Answer answer : partItem.getAnswerList()) {
				Long uuidAnswerId=answerMap.get(answer.getId());
				if(uuidAnswerId!=null){
					partItem.setId(uuidAnswerId);
					partItem.setValue(answer.getId());
					break;
				}
			}
		}
	}

	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}

	public List<PartItem> getPartItems() {
		return partItems;
	}

	public void setPartItems(List<PartItem> partItems) {
		this.partItems = partItems;
	}

}
