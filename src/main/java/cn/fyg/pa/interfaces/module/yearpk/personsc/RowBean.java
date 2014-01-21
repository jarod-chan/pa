package cn.fyg.pa.interfaces.module.yearpk.personsc;

import java.util.List;

import cn.fyg.pa.domain.model.person.Person;

public class RowBean {
	
	private Person rowPerson;
	
	private List<CellBean> cellBeans;

	public Person getRowPerson() {
		return rowPerson;
	}

	public void setRowPerson(Person rowPerson) {
		this.rowPerson = rowPerson;
	}

	public List<CellBean> getCellBeans() {
		return cellBeans;
	}

	public void setCellBeans(List<CellBean> cellBeans) {
		this.cellBeans = cellBeans;
	}
	
	

}
