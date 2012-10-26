package cn.fyg.pa.domain.model.questionaires.part;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 对调查问卷的不同部分分组
 */
@Entity
@Table(name="qs_part")
public class Part {
	
	@Id
	private Long id;
	
	private Long qtid;//调查问卷id
	
	private String name;//分组描述
	
	@Enumerated(EnumType.STRING)
	private PartEnum type;//问题类型，简答题或者选择题

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PartEnum getType() {
		return type;
	}

	public void setType(PartEnum type) {
		this.type = type;
	}
	
	
}
