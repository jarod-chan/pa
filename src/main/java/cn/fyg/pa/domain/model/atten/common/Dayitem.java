package cn.fyg.pa.domain.model.atten.common;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import cn.fyg.pa.infrastructure.util.DateTool;

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
	
	public BigDecimal sub(Dayitem another){
		Calendar thisday=DateTool.create(this.year, this.month, this.day);
		Calendar anotherday=DateTool.create(another.getYear(), another.getMonth(), another.getDay());
		//计算相差的天数
		long betweenHalfdays=(thisday.getTimeInMillis()-anotherday.getTimeInMillis())/(1000*3600*24)*2+another.getAmpm().value()-this.ampm.value()+1; 
		return new BigDecimal(betweenHalfdays).divide(new BigDecimal("2"), 2, BigDecimal.ROUND_HALF_UP);
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
