package cn.fyg.pa.domain.model.busiout;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class Dayitem implements Comparable<Dayitem> {

	
	@Column(name="date_")
	private Long date;//日期
	
	@Enumerated(EnumType.STRING)
	private AMPM ampm;//上下午

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public AMPM getAmpm() {
		return ampm;
	}

	public void setAmpm(AMPM ampm) {
		this.ampm = ampm;
	}
	

	@Override
	public int compareTo(Dayitem another) {
		if(another==null)
			throw new NullPointerException();
		Long thisvalue=(this.date-1)*2+this.ampm.value();
		Long anotherValue=(another.getDate()-1)*2+another.getAmpm().value();
		return thisvalue.compareTo(anotherValue);
	}
	
	
	

}
