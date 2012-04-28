package cn.fyg.pa.domain.yearchk;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fychkmange implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long mangeid;
	private Long personid;
	private Long itemid;
	private Long val;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMangeid() {
		return mangeid;
	}
	public void setMangeid(Long mangeid) {
		this.mangeid = mangeid;
	}
	public Long getPersonid() {
		return personid;
	}
	public void setPersonid(Long personid) {
		this.personid = personid;
	}
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public Long getVal() {
		return val;
	}
	public void setVal(Long val) {
		this.val = val;
	}
}
