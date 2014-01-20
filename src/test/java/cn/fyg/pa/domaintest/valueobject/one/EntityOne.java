package cn.fyg.pa.domaintest.valueobject.one;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class EntityOne {
	
	@Id
	private Long id;
	
	private String name;

	@Embedded
	private ValueOne valueOne;

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public ValueOne getValueOne() {
		return valueOne;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValueOne(ValueOne valueOne) {
		this.valueOne = valueOne;
	}

	

}
