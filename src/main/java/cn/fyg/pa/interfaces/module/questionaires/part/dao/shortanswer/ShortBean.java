package cn.fyg.pa.interfaces.module.questionaires.part.dao.shortanswer;

import cn.fyg.pa.domain.model.questionaires.problem.ShortAnswer;

public class ShortBean {
	
	private ShortAnswer shortAnswer;
	
	private Long id;//答案记录id
	
	private Long problemid;//问题id
	
	private String value;//文本值

	public ShortAnswer getShortAnswer() {
		return shortAnswer;
	}

	public void setShortAnswer(ShortAnswer shortAnswer) {
		this.shortAnswer = shortAnswer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProblemid() {
		return problemid;
	}

	public void setProblemid(Long problemid) {
		this.problemid = problemid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
