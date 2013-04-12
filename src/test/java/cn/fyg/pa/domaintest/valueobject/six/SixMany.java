package cn.fyg.pa.domaintest.valueobject.six;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SixMany {
	
	@Id
	private Long id;
	
	private String many;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMany() {
		return many;
	}

	public void setMany(String many) {
		this.many = many;
	}
	

}
