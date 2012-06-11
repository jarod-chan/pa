package cn.fyg.pa.domain.model.person;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum ManageEnum implements CommonEnum {
	A("管理员"),
	G("分管副总"),
	Y("经理"),
	N("员工"),
	F("财务");
	
	private String name;
	private ManageEnum(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
