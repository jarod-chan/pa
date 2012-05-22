package cn.fyg.pa.domain.deptkpi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.apache.commons.lang.StringUtils;

import cn.fyg.pa.domain.companykpi.IdrCompany;
import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptindicator.DeptIndicator;
import cn.fyg.pa.domain.deptindicator.IndicatorOption;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.shared.Result;

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
			cascade = {CascadeType.REFRESH},
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
	
	/**
	 * 返回某个公司指标的分解
	 * @param idrCompany
	 */
	public DeptKpi getDeptKpiViewByIdrCompany(IdrCompany idrCompany){
		DeptKpi deptKpi=new DeptKpi();
		deptKpi.setId(this.id);
		deptKpi.setDepartment(this.department);
		deptKpi.setYear(this.year);
		List<DeptKpiItem> copyDeptKpiItems=new ArrayList<DeptKpiItem>();
		for(DeptKpiItem deptKpiItem:this.deptKpiItems){
			if(deptKpiItem.getIdrCompany().getId().equals(idrCompany.getId())){
				copyDeptKpiItems.add(deptKpiItem);
			}
		}
		deptKpi.setDeptKpiItems(copyDeptKpiItems);
		return deptKpi;
 	}
	
	public Result verifySelf(DeptIndicator deptIndicator){
		for(DeptKpiItem deptKpiItem:this.deptKpiItems){
			if(StringUtils.isNotBlank(deptKpiItem.getContext())){
				return new Result().pass(false).cause("部门指标内容不能为空");
			}
		}
		Set<Long> hasBreakSet = getBreakSet();
		for(IndicatorOption indicatorOption:deptIndicator.getIndiactorOptions()){
			if(isMustSelectNotInHasBreakSet(indicatorOption, hasBreakSet)){
				return new Result().pass(false).cause("必选公司指标未被分解");
			}
		}
		return new Result().pass(true);
	}

	private boolean isMustSelectNotInHasBreakSet(IndicatorOption indicatorOption,
			Set<Long> hasBreakSet) {
		return indicatorOption.getMust().booleanValue()
				&&!hasBreakSet.contains(indicatorOption.getIdrCompany().getId());
	}

	/**
	 * 获得已经被分解的项目
	 * @return
	 */
	private Set<Long> getBreakSet() {
		Set<Long> hasBreakSet=new HashSet<Long>();
		for(DeptKpiItem deptKpiItem:this.deptKpiItems){
			hasBreakSet.add(deptKpiItem.getIdrCompany().getId());
		}
		return hasBreakSet;
	}

}
