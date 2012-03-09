package cn.fyg.pa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

//年度指标
@Entity
public class IdrYear {
	
	@Id
	private Long year;

	@OneToMany(mappedBy = "idrYear",
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = MonthChkItem.class)
	@OrderBy("sn ASC")
	private List<IdrTypeWight> idrTypeWight=new ArrayList<IdrTypeWight>();
	
	@OneToMany(mappedBy = "idrYear",
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = MonthChkItem.class)
	@OrderBy("sn ASC")
	private List<IdrCompany> idrCompany=new ArrayList<IdrCompany>();
	
	
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public List<IdrTypeWight> getIdrTypeWight() {
		return idrTypeWight;
	}

	public void setIdrTypeWight(List<IdrTypeWight> idrTypeWight) {
		this.idrTypeWight = idrTypeWight;
	}

	public List<IdrCompany> getIdrCompany() {
		return idrCompany;
	}

	public void setIdrCompany(List<IdrCompany> idrCompany) {
		this.idrCompany = idrCompany;
	}
	
}
