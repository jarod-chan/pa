package cn.fyg.pa.interfaces.module.person.yearchk;

import cn.fyg.pa.domain.model.person.Person;

public class ResultBean {
	
	private Person person;
	
	private Long win=new Long(0);
	
	private Long draw=new Long(0);
	
	private Long lose=new Long(0);

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Long getWin() {
		return win;
	}

	public void setWin(Long win) {
		this.win = win;
	}

	public Long getDraw() {
		return draw;
	}

	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public Long getLose() {
		return lose;
	}

	public void setLose(Long lose) {
		this.lose = lose;
	}
	
	

}
