package cn.fyg.pa.domain.model.questionaires.problem;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *选择题
 */
@Entity
@Table(name="qs_choice")
public class Choice {
	
	@Id
	private Long id;
	
	private Long qtid;//调查问卷id
	
	private Long partid;//部分id
	
	private String subject;//问题内容
	
	@OneToMany(fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = Answer.class)
	@JoinColumn(name="problem_id")
	@OrderBy("id ASC")
	private List<Answer> answers;//问题答案

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQtid() {
		return qtid;
	}

	public void setQtid(Long qtid) {
		this.qtid = qtid;
	}

	public Long getPartid() {
		return partid;
	}

	public void setPartid(Long partid) {
		this.partid = partid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	

}
