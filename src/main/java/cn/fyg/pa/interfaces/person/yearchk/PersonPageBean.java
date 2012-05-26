package cn.fyg.pa.interfaces.person.yearchk;

import java.util.List;

public class PersonPageBean {
	
	private Long year;
	
	private PersonChkBean personChkBean;
	
	private List<CellBean> cellBeans;

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public PersonChkBean getPersonChkBean() {
		return personChkBean;
	}

	public void setPersonChkBean(PersonChkBean personChkBean) {
		this.personChkBean = personChkBean;
	}

	public List<CellBean> getCellBeans() {
		return cellBeans;
	}

	public void setCellBeans(List<CellBean> cellBeans) {
		this.cellBeans = cellBeans;
	}
	
	public void calculateSelf(){
		for (CellBean cellBean : this.cellBeans) {
			if(cellBean.getFycheck().getId()!=null){
				if(cellBean.getFycheck().getVal().equals(1L)){
					this.personChkBean.setWin(this.personChkBean.getWin()+1);
				}else if(cellBean.getFycheck().getVal().equals(0L)){
					this.personChkBean.setDraw(this.personChkBean.getDraw()+1);
				}else if(cellBean.getFycheck().getVal().equals(-1L)){
					this.personChkBean.setLose(this.personChkBean.getLose()+1);
				}
			}
		}
	}

}
