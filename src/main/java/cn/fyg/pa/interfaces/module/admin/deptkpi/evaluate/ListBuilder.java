package cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.deptkpi.DeptKpi;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.list.ItemBean;
import cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.list.PageBean;

public class ListBuilder {
	
	private DeptKpi deptKpi;

	public ListBuilder(DeptKpi deptKpi) {
		this.deptKpi = deptKpi;
	}
	
	public PageBean build(){
		PageBean pageBean = new PageBean();
		pageBean.setYear(this.deptKpi.getYear());
		pageBean.setDepartment(this.deptKpi.getDepartment());
		pageBean.setPageItems(createPageItems());
		return pageBean;
	}

	private List<ItemBean> createPageItems() {
		List<ItemBean> retList=new ArrayList<ItemBean>();
		ItemBean itemBean=null;
		for (DeptKpiItem deptKpiItem : this.deptKpi.getDeptKpiItems()) {
			if(!sameIdrCompanyId(itemBean,deptKpiItem)){
				itemBean=createItemBean(deptKpiItem);
				retList.add(itemBean);
			}
			itemBean.setDeptKpiItemNum(itemBean.getDeptKpiItemNum()+1);
			if(deptKpiItem.getResult()!=null){
				itemBean.setHasEvaluateItemNum(itemBean.getHasEvaluateItemNum()+1);
			}
		}
		return retList;
	}

	private ItemBean createItemBean(DeptKpiItem deptKpiItem) {
		ItemBean itemBean=new ItemBean();
		itemBean.setIdrCompany(deptKpiItem.getIdrCompany());
		itemBean.setDeptKpiItemNum(0);
		itemBean.setHasEvaluateItemNum(0);
		return itemBean;
	}

	//XXX  应该学习ddd中把实体比较加以抽象才对
	private boolean sameIdrCompanyId(ItemBean prevItemBean,DeptKpiItem deptKpiItem) {
		if(prevItemBean==null) return false;
		if(deptKpiItem==null) return false;
		return prevItemBean.getIdrCompany().getId().equals(deptKpiItem.getIdrCompany().getId());
	}
	
	
	
}
