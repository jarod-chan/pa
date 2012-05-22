package cn.fyg.pa.interfaces.deptkpi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.fyg.pa.domain.deptkpi.DeptKpi;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;

public class PreviewBuilder {
	
	private DeptKpi deptKpi;

	public PreviewBuilder(DeptKpi deptKpi) {
		super();
		this.deptKpi = deptKpi;
	}
	
	public PreviewPage build(){
		PreviewPage page=new PreviewPage();
		page.setYear(this.deptKpi.getYear());
		page.setDepartment(this.deptKpi.getDepartment());
		page.setPreviewItems(buildPreviewItems());
		return page;
	}

	private List<PreviewItem> buildPreviewItems() {
		if(this.deptKpi.getDeptKpiItems().isEmpty()){
			return null;
		}
		sortDeptKpiItems(this.deptKpi);
		
		List<PreviewItem> returnList=new ArrayList<PreviewItem>();
		PreviewItem previewItem=null;
		DeptKpiItem prevDeptKpiItem=null;
		
		for(DeptKpiItem deptKpiItem:this.deptKpi.getDeptKpiItems()){
			if(!compareIdrCompany(prevDeptKpiItem,deptKpiItem)){
				previewItem=createPreviewItem(deptKpiItem);
				returnList.add(previewItem);
			}
			previewItem.getDeptKpiItems().add(deptKpiItem);
			prevDeptKpiItem=deptKpiItem;
		}
		
		return returnList;
	}

	private boolean compareIdrCompany(DeptKpiItem prevDeptKpiItem,
			DeptKpiItem deptKpiItem) {
		if(prevDeptKpiItem==null||deptKpiItem==null){
			return false;
		}
		return prevDeptKpiItem.getIdrCompany().getId().equals(deptKpiItem.getIdrCompany().getId());
	}


	private PreviewItem createPreviewItem(DeptKpiItem deptKpiItem) {
		PreviewItem previewItem=new PreviewItem();
		previewItem.setIdrCompany(deptKpiItem.getIdrCompany());
		return previewItem;
	}

	private void sortDeptKpiItems(DeptKpi deptKpi) {
		List<DeptKpiItem> deptKpiItems = this.deptKpi.getDeptKpiItems();
		Collections.sort(deptKpiItems, new DeptKpiItemComparator());
		
	}
	
	public class DeptKpiItemComparator implements Comparator<DeptKpiItem> {
		@Override
		public int compare(DeptKpiItem one, DeptKpiItem two) {
			if(one.getIdrCompany().getSn().compareTo(two.getIdrCompany().getSn())==0){
				return one.getSn().compareTo(two.getSn());
			}
			return one.getIdrCompany().getSn().compareTo(two.getIdrCompany().getSn());
		}

	}

}
