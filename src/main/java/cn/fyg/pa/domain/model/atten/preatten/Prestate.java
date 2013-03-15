package cn.fyg.pa.domain.model.atten.preatten;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum Prestate implements CommonEnum {
	new_("新建"),
	committed("已提交"),
	confirmed("已确认"),
	voided("作废");

	private String name;
	
	private Prestate(String name){
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

}
