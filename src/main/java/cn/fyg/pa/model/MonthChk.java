package cn.fyg.pa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cn.fyg.pa.model.enums.StateEnum;

@Entity
public class MonthChk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	private Long year;
	private Long month;

	@Enumerated(EnumType.STRING)
	private StateEnum state;

	@OneToMany(mappedBy = "monthchk_id",
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE },
			targetEntity = MonthChkItem.class)
	private List<MonthChkItem> monthChkItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

	public List<MonthChkItem> getMonthChkItems() {
		return monthChkItems;
	}

	public void setMonthChkItems(List<MonthChkItem> monthChkItems) {
		this.monthChkItems = monthChkItems;
	}

}
