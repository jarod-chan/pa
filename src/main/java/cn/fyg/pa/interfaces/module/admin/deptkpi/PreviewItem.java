package cn.fyg.pa.interfaces.module.admin.deptkpi;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;

public class PreviewItem {
	
	
	private IdrCompany idrCompany;//指标
	
	private List<DeptKpiItem> deptKpiItems=new ArrayList<DeptKpiItem>();

	public IdrCompany getIdrCompany() {
		return idrCompany;
	}

	public void setIdrCompany(IdrCompany idrCompany) {
		this.idrCompany = idrCompany;
	}

	public List<DeptKpiItem> getDeptKpiItems() {
		return deptKpiItems;
	}

	public void setDeptKpiItems(List<DeptKpiItem> deptKpiItems) {
		this.deptKpiItems = deptKpiItems;
	}

}
