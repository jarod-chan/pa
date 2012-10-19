package cn.fyg.pa.domain.model.questionaires.key;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *校验码
 */
@Entity
@Table(name="qs_key")
public class Key {
	
	@Id
	private String uuid;//uuid,作为用户满意度调查的关键字

	private Long qtid;//调查问卷id
	
	@Enumerated(EnumType.STRING)
	private KeyState state;//uuid使用情况

	public Long getQtid() {
		return qtid;
	}

	public void setQtid(Long qtid) {
		this.qtid = qtid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public KeyState getState() {
		return state;
	}

	public void setState(KeyState state) {
		this.state = state;
	}
	
	
	
	
}
