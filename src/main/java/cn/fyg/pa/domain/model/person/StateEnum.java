package cn.fyg.pa.domain.model.person;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum StateEnum implements CommonEnum {
	valid("有效"),invalid("无效") ;

	private String name;
	
	private StateEnum(String name) {
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
