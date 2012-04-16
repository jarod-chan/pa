package cn.fyg.pa.domain.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


@Entity(name = "fyperson")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String name;

	private String chkstr;
	
	@Enumerated(EnumType.STRING)
	private TypeEnum type;
	
	private String department;
	
	@Enumerated(EnumType.STRING)
	private ManageEnum manage;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ManageEnum getManage() {
		return manage;
	}

	public void setManage(ManageEnum manage) {
		this.manage = manage;
	}

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

	public String getChkstr() {
		return chkstr;
	}

	public void setChkstr(String chkstr) {
		this.chkstr = chkstr;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

}
