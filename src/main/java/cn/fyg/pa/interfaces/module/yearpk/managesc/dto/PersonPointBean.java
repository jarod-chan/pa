package cn.fyg.pa.interfaces.module.yearpk.managesc.dto;

import cn.fyg.pa.domain.model.person.Person;

public class PersonPointBean {
	
	private Person person;
	private String getPoint;
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getGetPoint() {
		return getPoint;
	}
	public void setGetPoint(String getPoint) {
		this.getPoint = getPoint;
	}

}
