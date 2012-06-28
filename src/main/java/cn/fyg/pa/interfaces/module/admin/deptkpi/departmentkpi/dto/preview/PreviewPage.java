package cn.fyg.pa.interfaces.module.admin.deptkpi.departmentkpi.dto.preview;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.department.Department;

public class PreviewPage {
	
	private Long year;
	
	private Department department;
	
	private List<PreviewItem> previewItems=new ArrayList<PreviewItem>();

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

	public List<PreviewItem> getPreviewItems() {
		return previewItems;
	}

	public void setPreviewItems(List<PreviewItem> previewItems) {
		this.previewItems = previewItems;
	}
	
	

}
