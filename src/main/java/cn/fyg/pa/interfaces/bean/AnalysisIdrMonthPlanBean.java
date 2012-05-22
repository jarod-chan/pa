package cn.fyg.pa.interfaces.bean;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanEnum;

public class AnalysisIdrMonthPlanBean {

	private Long year;

	private Long month;

	private int totalNum = 0;

	private int finishNum = 0;
	
	private List<DepartmentIdrMonthPlanBillStateBean> departmentIdrMonthPlanBillStateBeans=new ArrayList<DepartmentIdrMonthPlanBillStateBean>();

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getFinishNum() {
		return finishNum;
	}

	public void setFinishNum(int finishNum) {
		this.finishNum = finishNum;
	}

	public List<DepartmentIdrMonthPlanBillStateBean> getDepartmentIdrMonthPlanBillStateBeans() {
		return departmentIdrMonthPlanBillStateBeans;
	}

	public void setDepartmentIdrMonthPlanBillStateBeans(
			List<DepartmentIdrMonthPlanBillStateBean> departmentIdrMonthPlanBillStateBeans) {
		this.departmentIdrMonthPlanBillStateBeans = departmentIdrMonthPlanBillStateBeans;
	}

	public void calculateSelf() {
		this.totalNum=this.departmentIdrMonthPlanBillStateBeans.size();
		for(DepartmentIdrMonthPlanBillStateBean departmentIdrMonthPlanBillStateBean:this.departmentIdrMonthPlanBillStateBeans){
			if(departmentIdrMonthPlanBillStateBean.getState()==IdrMonthPlanEnum.FINISHED){
				this.finishNum++;
			}
		}
	}

}
