package cn.fyg.pa.interfaces.deptkpi;

import cn.fyg.pa.domain.companykpi.IdrCompany;

public class ListItem {
	
	private IdrCompany idrCompany;//指标

	private int deptKpiItemNum; //已分解项数
	
	private boolean mustSelect;//是否必选

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
