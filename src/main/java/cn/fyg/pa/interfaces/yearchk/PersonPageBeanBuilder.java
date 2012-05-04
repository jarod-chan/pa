package cn.fyg.pa.interfaces.yearchk;

import java.util.List;

import cn.fyg.pa.domain.person.Person;

public class PersonPageBeanBuilder {
	
	private List<CellBean> cellBeans;
	
	public PersonPageBeanBuilder(List<CellBean> cellBeans) {
		super();
		this.cellBeans=cellBeans;
	}
	
	public PersonPageBean build(Long year,Person person){
		PersonPageBean pageBean=new PersonPageBean();
		pageBean.setYear(year);
		PersonChkBean personChkBean=new PersonChkBean();
		personChkBean.setId(person.getId().intValue());
		personChkBean.setDepartment(person.getDepartment());
		personChkBean.setName(person.getName());
		personChkBean.setWin(0);
		personChkBean.setDraw(0);
		personChkBean.setLose(0);
		pageBean.setPersonChkBean(personChkBean);
		pageBean.setCellBeans(this.cellBeans);
		pageBean.calculateSelf();
		return pageBean;
	}

}
