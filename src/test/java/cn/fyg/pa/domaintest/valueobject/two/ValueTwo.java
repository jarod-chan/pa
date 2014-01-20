package cn.fyg.pa.domaintest.valueobject.two;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ValueTwo {
	
	@Column(name="value_name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
