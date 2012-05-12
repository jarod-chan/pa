package cn.fyg.pa.domain.deptindicator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;

import cn.fyg.pa.domain.department.Department;


/**
 * 年度部门指标分配，记录每一年对一个部门的指标分配结果
 * @author jhon.chen@gmail.com
 *
 */
@Entity
public class DeptIndicator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;//部门指标id

	private Long year;//年度

	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;//部门

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(joinColumns=@JoinColumn(name="deptindiactor_id"))
	@OrderBy("sn ASC")
	private List<IndicatorOption> indiactorOptions=new ArrayList<IndicatorOption>();//指标分配明细

	public Department getDepartment() {
		return department;
	}

	public Long getId() {
		return id;
	}


	public Long getYear() {
		return year;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setYear(Long year) {
		this.year = year;
	}

	public List<IndicatorOption> getIndiactorOptions() {
		return indiactorOptions;
	}

	public void setIndiactorOptions(List<IndicatorOption> indiactorOptions) {
		this.indiactorOptions = indiactorOptions;
	}

}
