package cn.fyg.pa.interfaces.deptkpi;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.companykpi.IdrCompany;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;

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
