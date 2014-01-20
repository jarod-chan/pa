package cn.fyg.pa.domaintest.mappedclass;

import javax.persistence.Entity;

@Entity
public class SonA extends Father {
	
	private String a_name;

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	

}
