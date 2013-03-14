package cn.fyg.pa.domain.model.busiout;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Dayitem {
	
	public static final BigDecimal ITEM_VALUE=new BigDecimal("0.5");
	
	@Column(name="date_")
	@Temporal(TemporalType.DATE)
	private Date date;//日期
	
	@Enumerated(EnumType.STRING)
	private AMPM ampm;//上下午

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AMPM getAmpm() {
		return ampm;
	}

	public void setAmpm(AMPM ampm) {
		this.ampm = ampm;
	}
	
	

}
