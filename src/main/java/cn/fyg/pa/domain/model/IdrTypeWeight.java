package cn.fyg.pa.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IdrTypeWeight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;//id
	
	private Long sn;//sn
		
	@ManyToOne
	@JoinColumn(name="idrIdrYearTypeWeight_id")
	private IdrYearTypeWeight idrYearTypeWeight;//权重年度
	
	@ManyToOne
	@JoinColumn(name="idrtype_id")
	private IdrType idrType;
	
	@Column(precision=12, scale=2)
	private BigDecimal weight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IdrType getIdrType() {
		return idrType;
	}

	public void setIdrType(IdrType idrType) {
		this.idrType = idrType;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public IdrYearTypeWeight getIdrYearTypeWeight() {
		return idrYearTypeWeight;
	}

	public void setIdrYearTypeWeight(IdrYearTypeWeight idrYearTypeWeight) {
		this.idrYearTypeWeight = idrYearTypeWeight;
	}

}
