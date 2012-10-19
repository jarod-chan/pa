package cn.fyg.pa.domain.model.questionaires.ques;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *问卷调查
 */
@Entity
@Table(name="qs_ques")
public class Ques {
	@Id
	private Long id;
	
	private String name;//调查问卷名称
	
	@Enumerated(EnumType.STRING)
	private QuesState state;//问卷执行状态

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QuesState getState() {
		return state;
	}

	public void setState(QuesState state) {
		this.state = state;
	}
	
	

}
