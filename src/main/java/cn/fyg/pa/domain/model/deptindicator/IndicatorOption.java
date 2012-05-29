package cn.fyg.pa.domain.model.deptindicator;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.fyg.pa.domain.model.companykpi.IdrCompany;

/**
 * 部门指标明细，表明该公司指标是否部门必须达成的指标
 * sn: 表示显示的顺序
 * must: true 时表示是必选完成的，false表示可选
 * @author jhon.chen@gmail.com
 *
 */
@Embeddable
public class IndicatorOption {
	
	private Long sn;//序号
	
	private Boolean must;//是否必选
	
	@ManyToOne
	@JoinColumn(name="idrcompany_id")
	private IdrCompany idrCompany;//公司指标

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public Boolean getMust() {
		return must;
	}

	public void setMust(Boolean must) {
		this.must = must;
	}

	public IdrCompany getIdrCompany() {
		return idrCompany;
	}

	public void setIdrCompany(IdrCompany idrCompany) {
		this.idrCompany = idrCompany;
	}
	
	
}
