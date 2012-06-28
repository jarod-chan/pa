package cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.list;

import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;

public class ItemBean {
	
	private IdrCompany idrCompany;//指标

	private int deptKpiItemNum; //已分解项数
	
	private int hasEvaluateItemNum; //已评价项数

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

	public int getHasEvaluateItemNum() {
		return hasEvaluateItemNum;
	}

	public void setHasEvaluateItemNum(int hasEvaluateItemNum) {
		this.hasEvaluateItemNum = hasEvaluateItemNum;
	}
	
	

}
