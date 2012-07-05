package cn.fyg.pa.interfaces.module.admin.deptkpi.preview;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.deptkpi.DeptKpi;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.interfaces.module.admin.deptkpi.preview.dto.PreviewItem;
import cn.fyg.pa.interfaces.module.admin.deptkpi.preview.dto.PreviewPage;

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

}
