package cn.fyg.pa.domain.model.department;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {
	
	@Id
	private Long id;
	
	private String number;//编码
	
	private String name;//名称
	
	private Long gmange_id;//分管副总

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGmange_id() {
		return gmange_id;
	}

	public void setGmange_id(Long gmange_id) {
		this.gmange_id = gmange_id;
	}
	
}
