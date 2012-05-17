package cn.fyg.pa.interfaces.deptkpi;

import java.util.List;

import cn.fyg.pa.domain.department.Department;

public class ListPage {
	
	private Long year;
	
	private Department department;
	
	private List<PageItem> pageItems;

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

	public List<PageItem> getPageItems() {
		return pageItems;
	}

	public void setPageItems(List<PageItem> pageItems) {
		this.pageItems = pageItems;
	}
	
}
