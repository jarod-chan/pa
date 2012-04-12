package cn.fyg.pa.application.bean;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.enums.MonthChkEnum;

public class AnalysisDepartmentBean {
	
	private String departmentName;
	
	private int totalNum=0;
	
	private int finishNum=0;
	
	private List<PersonMonthChkStateBean> personMonthChkStateBeans=new ArrayList<PersonMonthChkStateBean>();
	

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public List<PersonMonthChkStateBean> getPersonMonthChkStateBeans() {
		return personMonthChkStateBeans;
	}

	public void setPersonMonthChkStateBeans(
			List<PersonMonthChkStateBean> personMonthChkStateBeans) {
		this.personMonthChkStateBeans = personMonthChkStateBeans;
	}
	
	public void calculateSelf(){
		totalNum=this.personMonthChkStateBeans.size();
		for(PersonMonthChkStateBean personMonthChkStateBean:this.personMonthChkStateBeans){
			if(personMonthChkStateBean.getState()==MonthChkEnum.FINISHED){
				finishNum ++;
			}
		}
	}
	
}
