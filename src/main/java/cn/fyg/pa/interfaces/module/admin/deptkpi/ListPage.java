package cn.fyg.pa.interfaces.module.admin.deptkpi;

import java.util.List;

import cn.fyg.pa.domain.department.Department;

public class ListPage {
	
	private Long year;
	
	private Department department;
	
	private List<ListItem> pageItems;

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

	public List<ListItem> getPageItems() {
		return pageItems;
	}

	public void setPageItems(List<ListItem> pageItems) {
		this.pageItems = pageItems;
	}
	
}
