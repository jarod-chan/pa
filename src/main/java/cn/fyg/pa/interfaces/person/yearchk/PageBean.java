package cn.fyg.pa.interfaces.person.yearchk;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	
	private Long year;
	
	private int needChkPerson;
	
	private boolean finish;
	
	private List<DepartmentChkBean> departmentChkBeans=new ArrayList<DepartmentChkBean>();

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}


	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
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
				this.finish=false;
				return;
			}
			this.finish=true;
		}
	}

}
