package cn.fyg.pa.domain.model.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fyperson")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String name;

	private String chkstr;
	
	/**
	 * 职能部室，项目部所有部门分成两部分
	 */
	@Enumerated(EnumType.STRING)
	private TypeEnum type;
	
	private String department;
	
	/**
	 *是否经理
	 */
	@Enumerated(EnumType.STRING)
	private ManageEnum manage;

	private String email;
	
	/**
	 * 有效，无效状态，用于处理员工离职情况
	 */
	@Enumerated(EnumType.STRING)
	private StateEnum state;

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

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}
    
	
	public String getKey(){
		String AT = "@";
		return this.email.substring(0,this.email.lastIndexOf(AT));
	}
	
}
