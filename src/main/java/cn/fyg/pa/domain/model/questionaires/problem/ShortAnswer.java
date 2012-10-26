package cn.fyg.pa.domain.model.questionaires.problem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *简答题
 */
@Entity
@Table(name="qs_shortanswer")
public class ShortAnswer {
	
	@Id
	private Long id;
	
	private Long qtid;//调查问卷id
	
	private Long partid;//部分id
	
	private String subject;//问题内容
	

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
	
	
	
}
