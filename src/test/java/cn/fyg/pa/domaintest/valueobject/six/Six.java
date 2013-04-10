package cn.fyg.pa.domaintest.valueobject.six;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Six {
	
	@Id
	private Long id;
	
	private String one;
	
	@OneToMany(
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = SixMany.class,
			orphanRemoval=true)
	@JoinColumn(name = "one_id")
	private List<SixMany> manys=new ArrayList<SixMany>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public List<SixMany> getManys() {
		return manys;
	}

	public void setManys(List<SixMany> manys) {
		this.manys = manys;
	}
	
	
	
}
