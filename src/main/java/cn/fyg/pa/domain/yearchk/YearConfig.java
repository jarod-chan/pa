package cn.fyg.pa.domain.yearchk;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class YearConfig {
	
	@Id
	private Long year;
	
	private Boolean enable;

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	public void enable(){
		this.enable=true;
	}
	
	public void disable(){
		this.enable=false;
	}

}
