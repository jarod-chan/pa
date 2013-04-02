package cn.fyg.pa.domain.model.atten.busiout;

import cn.fyg.pa.domain.shared.CommonEnum;


public enum BusiState implements CommonEnum {
	new_("新建"),
	committed("审批中"),
	passed("通过"),
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
