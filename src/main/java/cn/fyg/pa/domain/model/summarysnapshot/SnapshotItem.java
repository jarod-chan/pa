package cn.fyg.pa.domain.model.summarysnapshot;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.person.Person;

@Embeddable
public class SnapshotItem {
	
	private Long sn;
	
	@Enumerated(EnumType.STRING)
	private MonthChkEnum status;
	
	@ManyToOne
    @JoinColumn(name = "department_id")  
	private Department department;
	
	@ManyToOne
    @JoinColumn(name = "manager_id")
	private Person manager;
	
	@ManyToOne
    @JoinColumn(name = "person_id")
	private Person person;

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public MonthChkEnum getStatus() {
		return status;
	}

	public void setStatus(MonthChkEnum status) {
		this.status = status;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	

}
