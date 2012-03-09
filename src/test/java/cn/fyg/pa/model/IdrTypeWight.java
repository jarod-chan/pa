package cn.fyg.pa.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class IdrTypeWight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;//id
	
	private Long sn;//sn
	
	@Column
	@JoinColumn(name="idryear_id")
	private IdrYear idrYear;//权重年度
	
	@Column
	@JoinColumn(name="idrtype_id")
	private IdrType idrType;
	
	@Column(precision=12, scale=2)
	private BigDecimal wight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IdrYear getIdrYear() {
		return idrYear;
	}

	public void setIdrYear(IdrYear idrYear) {
		this.idrYear = idrYear;
	}

	public IdrType getIdrType() {
		return idrType;
	}

	public void setIdrType(IdrType idrType) {
		this.idrType = idrType;
	}

	public BigDecimal getWight() {
		return wight;
	}

	public void setWight(BigDecimal wight) {
		this.wight = wight;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	
}
