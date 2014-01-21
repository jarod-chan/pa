package cn.fyg.pa.interfaces.module.report.analysismonthchk.dto;

import java.util.ArrayList;
import java.util.List;


public class AnalysisMonthChkBean {
	
	private Long year;
	
	private Long month;
	
	private int totalNum=0;
	
	private int finishNum=0;
	
	private List<AnalysisDepartmentBean> analysisDepartmentBeans=new ArrayList<AnalysisDepartmentBean>();

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

	public List<AnalysisDepartmentBean> getAnalysisDepartmentBeans() {
		return analysisDepartmentBeans;
	}

	public void setAnalysisDepartmentBeans(
			List<AnalysisDepartmentBean> analysisDepartmentBeans) {
		this.analysisDepartmentBeans = analysisDepartmentBeans;
	}
	
	public void calculateSelf(){
		for(AnalysisDepartmentBean analysisDepartmentBean:this.analysisDepartmentBeans){
			analysisDepartmentBean.calculateSelf();
			totalNum+=analysisDepartmentBean.getTotalNum();
			finishNum+=analysisDepartmentBean.getFinishNum();
		}
	}

}
