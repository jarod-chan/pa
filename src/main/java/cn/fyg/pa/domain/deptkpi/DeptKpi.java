package cn.fyg.pa.domain.deptkpi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;

/**
 * 部门考核指标，通过year,department 来唯一确定一个
 * 部门kpi考核分解
 */
@Entity
public class DeptKpi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //部门kpi分解id
	
	private Long year; //年度
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;//部门
	
	@OneToMany(mappedBy = "deptKpi",
			fetch = FetchType.EAGER, 
			cascade = {},
			targetEntity = DeptKpiItem.class)
	@OrderBy("sn ASC")
	private List<DeptKpiItem> deptKpiItems=new ArrayList<DeptKpiItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<DeptKpiItem> getDeptKpiItems() {
		return deptKpiItems;
	}

	public void setDeptKpiItems(List<DeptKpiItem> deptKpiItems) {
		this.deptKpiItems = deptKpiItems;
	}
	


}
