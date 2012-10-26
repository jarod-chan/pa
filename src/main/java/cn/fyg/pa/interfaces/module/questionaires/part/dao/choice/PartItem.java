package cn.fyg.pa.interfaces.module.questionaires.part.dao.choice;

import java.util.List;

import cn.fyg.pa.domain.model.questionaires.problem.Answer;

public class PartItem {
	
	private Long id;
	
	private Long value;
	
	private int type;
	
	private List <Answer> answerList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
	
	

}
