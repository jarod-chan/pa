package cn.fyg.pa.domaintest.onetomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class One {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "one",
			fetch = FetchType.EAGER, 
			cascade = {},
			targetEntity = Many.class)
	private List<Many> manys=new ArrayList<Many>();

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

	public List<Many> getManys() {
		return manys;
	}

	public void setManys(List<Many> manys) {
		this.manys = manys;
	}

	@Override
	public String toString() {
		return "One [id=" + id + ", name=" + name + ", manys=" + manys + "]";
	}

	
	

}
