package cn.fyg.pa.domaintest.onetomany;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Many {
	
	@Id
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "one_id")
	private One one;
	
	private String type;

	private String name;
	
	
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public One getOne() {
		return one;
	}

	public String getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOne(One one) {
		this.one = one;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Many [id=" + id + ", one=" + one.getId() + ", type=" + type + ", name="
				+ name + "]";
	}
	
	

}
