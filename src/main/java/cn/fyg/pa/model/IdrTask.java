package cn.fyg.pa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class IdrTask {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long sn;//序号
	
	@ManyToOne
    @JoinColumn(name = "idrtaskbill_id")  
	private IdrTaskBill idrTaskBill;//任务单id
	
//	@ManyToOne
//    @JoinColumn(name = "parent_id")  
	@Transient
	private IdrTask parent;//父任务
	
	private String context;//工作内容
	
	@Column(length=512)
	private String summary;//工作小结

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public IdrTaskBill getIdrTaskBill() {
		return idrTaskBill;
	}

	public void setIdrTaskBill(IdrTaskBill idrTaskBill) {
		this.idrTaskBill = idrTaskBill;
	}

	public IdrTask getParent() {
		return parent;
	}

	public void setParent(IdrTask parent) {
		this.parent = parent;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
}
