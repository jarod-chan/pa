package cn.fyg.pa.interfaces.module.admin.deptkpi;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.deptkpi.DeptKpi;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;

public class EditPage {
	
	private DeptKpi deptKpi;

	private List<DeptKpiItem> deptKpiItems=new ArrayList<DeptKpiItem>();

	public List<DeptKpiItem> getDeptKpiItems() {
		return deptKpiItems;
	}

	public void setDeptKpiItems(List<DeptKpiItem> deptKpiItems) {
		this.deptKpiItems = deptKpiItems;
	}

	public DeptKpi getDeptKpi() {
		return deptKpi;
	}

	public void setDeptKpi(DeptKpi deptKpi) {
		this.deptKpi = deptKpi;
	}

	
}
