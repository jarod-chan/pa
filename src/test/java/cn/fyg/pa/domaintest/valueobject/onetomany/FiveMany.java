package cn.fyg.pa.domaintest.valueobject.onetomany;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FiveMany {
	
	@Id
	private Long id;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "one_id")
	private Five five;
	
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

	public Five getFive() {
		return five;
	}

	public void setFive(Five five) {
		this.five = five;
	}
	
	

}
