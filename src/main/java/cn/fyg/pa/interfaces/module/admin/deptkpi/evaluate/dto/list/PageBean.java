package cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.list;

import java.util.List;

import cn.fyg.pa.domain.model.department.Department;


public class PageBean {
	
	private Long year;
	
	private Department department;
	
	private List<ItemBean> pageItems;

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<ItemBean> getPageItems() {
		return pageItems;
	}

	public void setPageItems(List<ItemBean> pageItems) {
		this.pageItems = pageItems;
	}
	
	

}
