package cn.fyg.pa.domaintest.valueobject.one;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ValueOne {
	
	@Column(name="valueOne_name")
	private String valuename;

	public String getValuename() {
		return valuename;
	}

	public void setValuename(String valuename) {
		this.valuename = valuename;
	}
}
