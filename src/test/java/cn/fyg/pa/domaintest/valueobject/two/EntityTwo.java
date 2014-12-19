package cn.fyg.pa.domaintest.valueobject.two;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


@Entity
public class EntityTwo {

	@Id
	private Long id;
	
	private String name;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(joinColumns=@JoinColumn(name="entity_id"))
	private List<ValueTwo> valueTwo=new ArrayList<ValueTwo>();

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

	public List<ValueTwo> getValueTwo() {
		return valueTwo;
	}

	public void setValueTwo(List<ValueTwo> valueTwo) {
		this.valueTwo = valueTwo;
	}

	
}
