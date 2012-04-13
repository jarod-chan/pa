package cn.fyg.pa.domaintest;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class Value {
	
	private String number;
	
	@Column(name="value_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="eo_id",referencedColumnName="id")
	private EntityObject eo;

	public EntityObject getEo() {
		return eo;
	}

	public void setEo(EntityObject eo) {
		this.eo = eo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	

}
