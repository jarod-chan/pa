package cn.fyg.pa.domain.model.deptkpi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicator;
import cn.fyg.pa.domain.model.deptindicator.IndicatorOption;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.shared.Result;

/**
 * 部门考核指标，通过year,department 来唯一确定一个
 * 部门kpi考核分解
 */
@Entity
public class DeptKpi {
	
	
	/**
	 * 必选项分解总分值
	 */
	private static final Long MUST_SELECT_TOTAL_POINT=100L;
	
	
	/**
	 * 非必选项分解总分值
	 */
	private static final Long OTHER_TOTAL_POINT=50L;
	
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
	
	//XXX  重构此处，采用其它方式处理
	public Result verifySelf(DeptIndicator deptIndicator){
		if(deptIndicator==null){
			return new Result().pass(false).cause("部门指标未分配");
		}
		for(DeptKpiItem deptKpiItem:this.deptKpiItems){
			if(StringUtils.isBlank(deptKpiItem.getContext())){
				return new Result().pass(false).cause("部门指标内容不能为空");
			}
			if(deptKpiItem.getPoint()==null){
				return new Result().pass(false).cause("部门指标分值不能为空");
			}
		}
		Set<Long> hasBreakSet = getBreakSet();
		for(IndicatorOption indicatorOption:deptIndicator.getIndiactorOptions()){
			if(isMustSelectNotInHasBreakSet(indicatorOption, hasBreakSet)){
				return new Result().pass(false).cause("必选公司指标未被分解");
			}
		}
		Set<Long> mustSelectCompanyKpiIds=getMustSelectCompanyKpiIds(deptIndicator.getIndiactorOptions());
		Long mustSelectBreakPoint=getMustSelectBreakPoint(mustSelectCompanyKpiIds);
		if(!mustSelectBreakPoint.equals(MUST_SELECT_TOTAL_POINT)){
			return new Result().pass(false).cause(String.format("必选公司指标总分值为:%s,不等于%s。", mustSelectBreakPoint,MUST_SELECT_TOTAL_POINT));
		}
		Long otherBreakPoint=getOtherBreakPoint(mustSelectCompanyKpiIds);
		if(!otherBreakPoint.equals(OTHER_TOTAL_POINT)){
			return new Result().pass(false).cause(String.format("非公司必选指标总分值为:%s,不等于%s。",otherBreakPoint,OTHER_TOTAL_POINT));
		}
		
		return new Result().pass(true);
	}
	
	private Long getOtherBreakPoint(Set<Long> mustSelectCompanyKpiIds) {
		long totalPoint=0L;
		for(DeptKpiItem deptKpiItem:this.deptKpiItems){
			if(!mustSelectCompanyKpiIds.contains(deptKpiItem.getIdrCompany().getId())){
				totalPoint+=deptKpiItem.getPoint().longValue();
			}
		}
		return totalPoint;
	}

	private Long getMustSelectBreakPoint(Set<Long> mustSelectCompanyKpiIds) {
		long totalPoint=0L;
		for(DeptKpiItem deptKpiItem:this.deptKpiItems){
			if(mustSelectCompanyKpiIds.contains(deptKpiItem.getIdrCompany().getId())){
				totalPoint+=deptKpiItem.getPoint().longValue();
			}
		}
		return totalPoint;
	}

	private Set<Long> getMustSelectCompanyKpiIds(List<IndicatorOption> indiactorOptions) {
		Set<Long> retSet=new HashSet<Long>();
		for (IndicatorOption indicatorOption : indiactorOptions) {
			if(indicatorOption.getMust().booleanValue()){
				retSet.add(indicatorOption.getIdrCompany().getId());
			}
		}
		return retSet;
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
	
	public void sortByIdrCompanySnAndDeptKpiItemSn(){
		Collections.sort(deptKpiItems, new  Comparator<DeptKpiItem>(){
			@Override
			public int compare(DeptKpiItem one, DeptKpiItem two) {
				if(one.getIdrCompany().getSn().compareTo(two.getIdrCompany().getSn())==0){
					return one.getSn().compareTo(two.getSn());
				}
				return one.getIdrCompany().getSn().compareTo(two.getIdrCompany().getSn());
			}
			
		});
	}
	


}
