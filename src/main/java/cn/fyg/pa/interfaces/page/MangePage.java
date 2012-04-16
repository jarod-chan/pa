package cn.fyg.pa.interfaces.page;

import cn.fyg.pa.domain.person.Person;

public class MangePage {
	
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
