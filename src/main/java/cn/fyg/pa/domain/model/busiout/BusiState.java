package cn.fyg.pa.domain.model.busiout;

import cn.fyg.pa.domain.shared.CommonEnum;


public enum BusiState implements CommonEnum {
	new_("新建"),
	saved("保存"),
	committed("已提交"),
	confirmed("已确认"),
	voided("作废"); 
	
	private String name;

	private BusiState(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
