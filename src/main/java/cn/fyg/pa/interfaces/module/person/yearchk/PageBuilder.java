package cn.fyg.pa.interfaces.module.person.yearchk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.yearchk.Fycheck;

public class PageBuilder {
	
	private Long year;
	
	private Person chkPerson;
	
	private List<Person> personList;
	
	private Map<String,Fycheck> hasChecksValues;

	public PageBuilder(Long year,Person chkPerson,List<Person> personList,
			Map<String, Fycheck> hasChecksValues) {
		super();
		this.year = year;
		this.chkPerson = chkPerson;
		this.personList = personList;
		this.hasChecksValues = hasChecksValues;
	}

	public List<RowBean> createRowBeanList(){
		ArrayList<RowBean> rowBeanList = new ArrayList<RowBean>();
		for (Person person : this.personList) {
			RowBean rowBean=createRowBean(person);
			rowBeanList.add(rowBean);
		}
		rowBeanList=removeLast(rowBeanList);
		return rowBeanList;
	}

	private ArrayList<RowBean> removeLast(ArrayList<RowBean> rowBeanList) {
		for (RowBean rowBean : rowBeanList) {
			rowBean.getCellBeans().remove(0);
		}
		rowBeanList.remove(rowBeanList.size()-1);
		return rowBeanList;
	}

	private RowBean createRowBean(Person rowPerson) {
		RowBean rowBean = new RowBean();
		rowBean.setRowPerson(rowPerson);
		ArrayList<CellBean> cellBeans = new ArrayList<CellBean>();
		for (Person colPerson : this.personList) {
			CellBean cellBean = createCellBean(colPerson, rowPerson);
			cellBeans.add(cellBean);
		}
		rowBean.setCellBeans(cellBeans);
		return rowBean;
	}

	private CellBean createCellBean(Person colPerson, Person rowPerson) {
		if(colPerson.getId().compareTo(rowPerson.getId())>0){
			CellBean cellBean = new CellBean();
			cellBean.setColPerson(colPerson);
			cellBean.setRowPerson(rowPerson);
			cellBean.setFycheck(getFycheck(colPerson, rowPerson));
			return cellBean;
		}
		return null;
	}

	private Fycheck getFycheck(Person colPerson, Person rowPerson) {
		Fycheck fycheck = hasChecksValues.get(colPerson.getId().toString()+":"+rowPerson.getId().toString());
		if(fycheck==null){
			fycheck=new Fycheck();
			fycheck.setYear(this.year);
			fycheck.setChkId(this.chkPerson.getId());
			fycheck.setColId(colPerson.getId());
			fycheck.setRowId(rowPerson.getId());
			fycheck.setVal(0L);
		}
		return fycheck;
	}

}
