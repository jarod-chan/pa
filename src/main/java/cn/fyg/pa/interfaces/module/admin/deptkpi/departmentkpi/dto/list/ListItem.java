package cn.fyg.pa.interfaces.module.admin.deptkpi.departmentkpi.dto.list;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;

public class ListItem {
	
	private IdrCompany idrCompany;//指标

	private int deptKpiItemNum; //已分解项数
	
	private boolean mustSelect;//是否必选
	
	private List<DeptKpiItem> deptKpiItems=new ArrayList<DeptKpiItem>();

	public List<DeptKpiItem> getDeptKpiItems() {
		return deptKpiItems;
	}

	public void setDeptKpiItems(List<DeptKpiItem> deptKpiItems) {
		this.deptKpiItems = deptKpiItems;
	}

	public IdrCompany getIdrCompany() {
		return idrCompany;
	}

	public void setIdrCompany(IdrCompany idrCompany) {
		this.idrCompany = idrCompany;
	}

	public int getDeptKpiItemNum() {
		return deptKpiItemNum;
	}

	public void setDeptKpiItemNum(int deptKpiItemNum) {
		this.deptKpiItemNum = deptKpiItemNum;
	}

	public boolean isMustSelect() {
		return mustSelect;
	}

	public void setMustSelect(boolean mustSelect) {
		this.mustSelect = mustSelect;
	}
}
