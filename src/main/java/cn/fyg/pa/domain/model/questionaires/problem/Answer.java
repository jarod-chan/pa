package cn.fyg.pa.domain.model.questionaires.problem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *选择题的预选答案
 */
@Entity
@Table(name="qs_answer")
public class Answer {
	
	@Id
	private Long id;
	
	private Long qtid;//调查问卷id
	
	private int type;//答案分组，解决出现分组的情况
	
	private String no;//答案序号
	
	private String name;//答案内容
	
	private String value;//值

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
