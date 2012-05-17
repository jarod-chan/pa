package cn.fyg.pa.interfaces.deptkpi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.companykpi.IdrCompany;
import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;

public class ListPageBuilder {
	
	private List<IdrCompany> idrCompanys;
	
	private List<DeptKpiItem> deptKpiItems;
	
	public ListPageBuilder(List<IdrCompany> idrCompanys,
			List<DeptKpiItem> deptKpiItems) {
		super();
		this.idrCompanys = idrCompanys;
		this.deptKpiItems = deptKpiItems;
	}


	public ListPage build(Long year, Department department){
		ListPage page=new ListPage();
		page.setYear(year);
		page.setDepartment(department);
		page.setPageItems(buildPageItems());
		return page;
	}


	private List<PageItem> buildPageItems() {
		Map<Long,Integer> deptKpiItemsByIdrCompany=createDeptKpiItemsByIdrCompany();
		List<PageItem> pageItems=createPageItems(deptKpiItemsByIdrCompany);
		return pageItems;
	}


	private List<PageItem> createPageItems(Map<Long, Integer> deptKpiItemsByIdrCompany) {
		List<PageItem> pageItems=new ArrayList<PageItem>();
		for (IdrCompany idrCompany : idrCompanys) {
			PageItem pageItem=new PageItem();
			pageItem.setIdrCompany(idrCompany);
			Long idrCompanyId = idrCompany.getId();
			Integer num=deptKpiItemsByIdrCompany.get(idrCompanyId);
			pageItem.setDeptKpiItemNum(num==null?0:num.intValue());
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
