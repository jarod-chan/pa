package cn.fyg.pa.domain.monthchk;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.fyg.pa.domain.worktype.WorkType;

@Entity
public class MonthChkItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "monthchk_id")  
	private MonthChk monthChk;
	
	private Long sn;
	
	@ManyToOne
    @JoinColumn(name = "worktype_id")  
	private WorkType workType;
	
	private String task;
	
	@Column
	private BigDecimal workhour;
	
	private Long point;
	
	public BigDecimal getWorkhour() {
		return this.workhour == null ?
				null : 
				workhour.setScale(1,BigDecimal.ROUND_HALF_UP);
	}

	public void setWorkhour(BigDecimal workhour) {
		if(workhour==null){
			this.workhour=null;
			return;
		}
		this.workhour = workhour.setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MonthChk getMonthChk() {
		return monthChk;
	}

	public void setMonthChk(MonthChk monthChk) {
		this.monthChk = monthChk;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}
	
}
