package cn.fyg.pa.model;

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
	@JoinColumn(name="idryear_id")
	private IdrYear idrYear;//权重年度
	
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

	public BigDecimal getWeight() {
		return this.weight==null?
				null:
				weight.setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public void setWeight(BigDecimal weight) {
		if(weight==null){
			this.weight =null;
			return;
		}	
		this.weight = weight.setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	
}
