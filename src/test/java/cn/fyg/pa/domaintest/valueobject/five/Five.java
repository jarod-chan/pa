package cn.fyg.pa.domaintest.valueobject.five;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Five {
	
	@Id
	private Long id;
	
	private String one;
	
	@OneToMany(mappedBy="five",
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = FiveMany.class,
			orphanRemoval=true)
//	@JoinColumn(name = "one_id")
	private List<FiveMany> manys=new ArrayList<FiveMany>();

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

	public List<FiveMany> getManys() {
		return manys;
	}

	public void setManys(List<FiveMany> manys) {
		this.manys = manys;
	}
	
	
	
}
