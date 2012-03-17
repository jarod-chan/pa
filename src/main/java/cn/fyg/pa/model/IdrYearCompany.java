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
public class IdrYearCompany {

	@Id
	private Long year;

	
	@OneToMany(mappedBy = "idrYearCompany",
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = IdrCompany.class)
	@OrderBy("sn ASC")
	private List<IdrCompany> idrCompany=new ArrayList<IdrCompany>();
	
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public List<IdrCompany> getIdrCompany() {
		return idrCompany;
	}

	public void setIdrCompany(List<IdrCompany> idrCompany) {
		this.idrCompany = idrCompany;
	}
	
}
