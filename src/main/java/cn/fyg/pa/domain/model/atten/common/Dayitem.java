package cn.fyg.pa.domain.model.atten.common;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Dayitem implements Comparable<Dayitem> {

	private Long year;//年
	
	private Long month;//月
	
	private Long day;
	
	@Enumerated(EnumType.STRING)
	private AMPM ampm;//上下午
	
	private Long value;
	
	public Long computeValue(){
		this.value=this.year*100000+this.month*1000+this.day*10+this.ampm.value();
		return this.value;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public AMPM getAmpm() {
		return ampm;
	}

	public void setAmpm(AMPM ampm) {
		this.ampm = ampm;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public int compareTo(Dayitem o) {
		Long thisLongValue=this.computeValue();
		Long anotherLongValue=o.computeValue();
		return thisLongValue.compareTo(anotherLongValue);
	}

	

}
