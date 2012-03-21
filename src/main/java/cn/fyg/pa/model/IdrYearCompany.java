package cn.fyg.pa.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	
	/**
	 * 按idrtypeweight排序 
	 */
	public void sortIdrCompanyByIdrTypeWeight(){
		Collections.sort(this.idrCompany, new IdrTypeWeightComparator());
		reIndex();
	}
	
	/**
	 * 重新排序
	 */
	private void reIndex(){
		for(int i=0,len=this.idrCompany.size();i<len;i++){
			this.idrCompany.get(i).setSn(1L+i);
		}
	}
	
	private class IdrTypeWeightComparator implements Comparator<IdrCompany>{
		@Override
		public int compare(IdrCompany one, IdrCompany two) {
			if(one.getIdrTypeWeight().getSn().compareTo(two.getIdrTypeWeight().getSn())==0){
				return one.getSn().compareTo(two.getSn());
			}
			return one.getIdrTypeWeight().getSn().compareTo(two.getIdrTypeWeight().getSn());
		}
	}
	
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
