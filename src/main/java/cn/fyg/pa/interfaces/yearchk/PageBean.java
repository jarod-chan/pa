package cn.fyg.pa.interfaces.yearchk;

import java.util.List;

public class PageBean {
	
	private Long year;
	
	private int needChkPerson;
	
	private boolean isFinish;
	
	private List<DepartmentChkBean> departmentChkBeans;

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}

	public List<DepartmentChkBean> getDepartmentChkBeans() {
		return departmentChkBeans;
	}

	public void setDepartmentChkBeans(List<DepartmentChkBean> departmentChkBeans) {
		this.departmentChkBeans = departmentChkBeans;
	}

	public int getNeedChkPerson() {
		return needChkPerson;
	}

	public void setNeedChkPerson(int needChkPerson) {
		this.needChkPerson = needChkPerson;
	}
	
	public void calculateSelf(){
		for(DepartmentChkBean departmentChkBean:this.departmentChkBeans){
			departmentChkBean.calculateSelf(this.needChkPerson);
			if(!departmentChkBean.isFinish()){
				this.isFinish=false;
				return;
			}
			this.isFinish=true;
		}
	}

}
