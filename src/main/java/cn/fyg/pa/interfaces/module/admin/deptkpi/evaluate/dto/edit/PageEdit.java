package cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.edit;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;

public class PageEdit {
	
	private List<DeptKpiItem> deptKpiItems=new ArrayList<DeptKpiItem>();

	public List<DeptKpiItem> getDeptKpiItems() {
		return deptKpiItems;
	}

	public void setDeptKpiItems(List<DeptKpiItem> deptKpiItems) {
		this.deptKpiItems = deptKpiItems;
	}
	
	
}
