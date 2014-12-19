package cn.fyg.pa.domain.model.yearchkstate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="am_yearchkstate")
public class YearchkState {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long year;
	
	private Long personId;
	
	@Enumerated(EnumType.STRING)
	private CheckState checkState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public CheckState getCheckState() {
		return checkState;
	}

	public void setCheckState(CheckState checkState) {
		this.checkState = checkState;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}
	
	
	
	
}
