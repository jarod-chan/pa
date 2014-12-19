package cn.fyg.pa.interfaces.web.module.monthlog.dto;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;

public class StatusBean {
	
	private MonthChkEnum status;
	
	private List<DepartDescBean>  departDescs;

		
	public void addDepartDescBean(DepartDescBean departDescBean){
		if(departDescs==null){
			departDescs=new ArrayList<DepartDescBean>();
		}
		if(departDescs.isEmpty()){
			departDescs.add(departDescBean);
			return;
		}
		DepartDescBean lastDepartDescBean=departDescs.get(departDescs.size()-1);
		if(lastDepartDescBean.getDepartemnt().getId().equals(departDescBean.getDepartemnt().getId())){
			return;
		}
		departDescs.add(departDescBean);
	}


	public MonthChkEnum getStatus() {
		return status;
	}

	public void setStatus(MonthChkEnum status) {
		this.status = status;
	}


	public List<DepartDescBean> getDepartDescs() {
		return departDescs;
	}


	public void setDepartDescs(List<DepartDescBean> departDescs) {
		this.departDescs = departDescs;
	}


}
