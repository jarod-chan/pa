package cn.fyg.pa.domaintest.valueobject.six;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SixMany {
	
	@Id
	private Long id;
	
/*	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "one_id")
	private Six six;*/
	
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

/*	public Six getSix() {
		return six;
	}

	public void setSix(Six six) {
		this.six = six;
	}
*/
	
	

}
