package cn.fyg.pa.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//公司指标
@Entity
public class IdrCompany{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;//id
	
	@ManyToOne
	@JoinColumn(name = "idryear_id")
	private IdrYear idrYear;//指标年度
	
	@Column(length=16)
	private String number;//编码
	
	@ManyToOne
	@JoinColumn(name = "idrtype_id")
	private IdrType idrType;//指标类型
	
	@Column(length=128)
	private String context;//内容
	
	@Column(length=64)
	private String quantization;//量化指标
	
	@Column(length=64)
	private String standard;//工作标准
	
	@Column(length=64)
	private String computeMode;//计算方式
	
	@Column(length=64)
	private String period;//考核周期
	
	@Column(precision=12, scale=2)
	private BigDecimal weight;//权重
	
	@Column(precision=12, scale=2)
	private BigDecimal realWight;//实际权重

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public IdrType getIdrType() {
		return idrType;
	}

	public void setIdrType(IdrType idrType) {
		this.idrType = idrType;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getQuantization() {
		return quantization;
	}

	public void setQuantization(String quantization) {
		this.quantization = quantization;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getComputeMode() {
		return computeMode;
	}

	public void setComputeMode(String computeMode) {
		this.computeMode = computeMode;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getRealWight() {
		return realWight;
	}

	public void setRealWight(BigDecimal realWight) {
		this.realWight = realWight;
	}
	
	
}
