package cn.fyg.pa.interfaces.module.admin.deptkpi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.companykpi.IdrCompany;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicator;
import cn.fyg.pa.domain.model.deptindicator.IndicatorOption;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;

public class ListBuilder {
	
	private List<IdrCompany> idrCompanys;
	
	private List<DeptKpiItem> deptKpiItems;
	
	private DeptIndicator deptIndicator;
	
	public ListBuilder(List<IdrCompany> idrCompanys,
			List<DeptKpiItem> deptKpiItems, DeptIndicator deptIndicator) {
		super();
		this.idrCompanys = idrCompanys;
		this.deptKpiItems = deptKpiItems;
		this.deptIndicator=deptIndicator;
	}


	public ListPage build(Long year, Department department){
		ListPage page=new ListPage();
		page.setYear(year);
		page.setDepartment(department);
		page.setPageItems(buildPageItems());
		return page;
	}


	private List<ListItem> buildPageItems() {
		Map<Long,Integer> deptKpiItemsByIdrCompany=createDeptKpiItemsByIdrCompany();
		Map<Long,Boolean> indicatorOptionByIdrCompany=createIndicatorOptionByIdrCompany();
		List<ListItem> pageItems=createPageItems(deptKpiItemsByIdrCompany,indicatorOptionByIdrCompany);
		return pageItems;
	}


	private Map<Long, Boolean> createIndicatorOptionByIdrCompany() {
		Map<Long,Boolean> returnMap=new HashMap<Long,Boolean>();
		for(IndicatorOption indicatorOption:this.deptIndicator.getIndiactorOptions()){
			returnMap.put(indicatorOption.getIdrCompany().getId(), indicatorOption.getMust());
		}
		return returnMap;
	}


	private List<ListItem> createPageItems(Map<Long, Integer> deptKpiItemsByIdrCompany, Map<Long, Boolean> indicatorOptionByIdrCompany) {
		List<ListItem> pageItems=new ArrayList<ListItem>();
		for (IdrCompany idrCompany : idrCompanys) {
			ListItem pageItem=new ListItem();
			pageItem.setIdrCompany(idrCompany);
			Long idrCompanyId = idrCompany.getId();
			Integer num=deptKpiItemsByIdrCompany.get(idrCompanyId);
			pageItem.setDeptKpiItemNum(num==null?0:num.intValue());
			Boolean mustSelect=indicatorOptionByIdrCompany.get(idrCompanyId);
			pageItem.setMustSelect(mustSelect==null?Boolean.FALSE:mustSelect.booleanValue());
			pageItems.add(pageItem);
		}
		return pageItems;
	}


	private Map<Long, Integer> createDeptKpiItemsByIdrCompany() {
		Map<Long,Integer> map = new HashMap<Long,Integer>();
		for ( DeptKpiItem deptKpiItem : deptKpiItems) {
			Long idrComapyId=deptKpiItem.getIdrCompany().getId();
			Integer num=map.get(idrComapyId);
			num=increaseOne(num);
			map.put(idrComapyId, num);
		}
		return map;
	}

	private Integer increaseOne(Integer num) {
		if(num==null){
			return Integer.valueOf(1);
		}
		int returnInt=num.intValue()+1;
		return Integer.valueOf(returnInt);
	}

}
