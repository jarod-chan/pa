package cn.fyg.pa.domaintest.valueobject.there;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;


@Entity
public class EntityObject {
	
	@Id
	private Long id;
	
	private String name;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(joinColumns=@JoinColumn(name="entity_id"))
	private List<Value> values=new ArrayList<Value>();
	
	@Embedded
	private Value vo;

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

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

	public Value getVo() {
		return vo;
	}

	public void setVo(Value vo) {
		this.vo = vo;
	}


	

}
