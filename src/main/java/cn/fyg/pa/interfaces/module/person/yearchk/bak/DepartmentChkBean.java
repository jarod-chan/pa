package cn.fyg.pa.interfaces.module.person.yearchk.bak;

import java.util.ArrayList;
import java.util.List;

public class DepartmentChkBean {
	
	private String department;
	
	private boolean isFinish;
	
	private List<PersonChkBean> personChkBeans=new ArrayList<PersonChkBean>();
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<PersonChkBean> getPersonChkBeans() {
		return personChkBeans;
	}

	public void setPersonChkBeans(List<PersonChkBean> personChkBeans) {
		this.personChkBeans = personChkBeans;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}

	public void calculateSelf(int needCheckPersons){
		for(PersonChkBean personChkBean:this.personChkBeans){
			int total=personChkBean.getWin()+personChkBean.getLose()+personChkBean.getDraw();
			if(total!=needCheckPersons){
				this.isFinish=false;
				return;
			}
			this.isFinish=true;
		}		
	}
}
