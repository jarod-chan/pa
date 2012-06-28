package cn.fyg.pa.domaintest.valueobject.four;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
public class Four {
	
	@Id
	private Long id;
	
	private String name;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(joinColumns=@JoinColumn(name="four_id"))
	private Set<Long> planMonths=new HashSet<Long>();
	
	@Transient
	private boolean[] monthCheck=new boolean[12];
	
	public boolean[] getMonthCheck() {
		for(int i=0;i<monthCheck.length;i++){
			this.monthCheck[i]=this.planMonths.contains(Long.valueOf(i+1))?true:false;
		}
		return this.monthCheck;
	}

	public void setMonthCheck(boolean[] monthCheck) {
		this.monthCheck = monthCheck;
		this.planMonths=new HashSet<Long>();
		for(int i=0;i<this.monthCheck.length;i++){
			if(this.monthCheck[i]){
				this.planMonths.add(Long.valueOf(i+1));
			}
			
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<Long> getPlanMonths() {
		return planMonths;
	}

	public void setPlanMonths(Set<Long> planMonths) {
		this.planMonths = planMonths;
	}

	@Override
	public String toString() {
		return "Four [id=" + id + ", name=" + name + ", planMonths="
				+ planMonths + ", monthCheck=" + Arrays.toString(getMonthCheck())
				+ "]";
	}

	
	
}
