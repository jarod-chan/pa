package cn.fyg.pa.interfaces.deptkpi;

import cn.fyg.pa.domain.companykpi.IdrCompany;

public class PageItem {
	
	private IdrCompany idrCompany;
	
	private int deptKpiItemNum;

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

	
}
