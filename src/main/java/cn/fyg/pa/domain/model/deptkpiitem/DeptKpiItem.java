package cn.fyg.pa.domain.model.deptkpiitem;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;
import cn.fyg.pa.domain.model.deptkpi.DeptKpi;

/**
 * 部门kpi分解明细，作为聚合根
 */
@Entity
public class DeptKpiItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;//部门kpi分解明细id
	
	private Long sn;//序号
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name = "deptkpi_id")
	private DeptKpi deptKpi;//部门kpi分解
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name = "idrcompany_id")
	private IdrCompany idrCompany;//公司指标
	
	@Column(length=128)
	private String context;//部门指标内容
	
	private Long point;//分值
	
	private Long result;//得分1-5等级
	
	@Column(length=512)
	private String resultDesc;//得分说明
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(joinColumns=@JoinColumn(name="deptkpiitem_id"))
	private Set<Long> planMonth=new HashSet<Long>();//计划
	
	@Transient
	private boolean[] monthCheck=new boolean[12];
	
	public boolean[] getMonthCheck() {
		for (int i = 0; i < monthCheck.length; i++) {
			this.monthCheck[i] = this.planMonth.contains(Long.valueOf(i + 1)) ? true : false;
		}
		return this.monthCheck;
	}

	public void setMonthCheck(boolean[] monthCheck) {
		this.monthCheck = monthCheck;
		this.planMonth = new HashSet<Long>();
		for (int i = 0; i < this.monthCheck.length; i++) {
			if (this.monthCheck[i]) {
				this.planMonth.add(Long.valueOf(i + 1));
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DeptKpi getDeptKpi() {
		return deptKpi;
	}

	public void setDeptKpi(DeptKpi deptKpi) {
		this.deptKpi = deptKpi;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public IdrCompany getIdrCompany() {
		return idrCompany;
	}

	public void setIdrCompany(IdrCompany idrCompany) {
		this.idrCompany = idrCompany;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public Set<Long> getPlanMonth() {
		return planMonth;
	}

	public void setPlanMonth(Set<Long> planMonth) {
		this.planMonth = planMonth;
	}

	

}
