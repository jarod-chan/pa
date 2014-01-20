package cn.fyg.pa.domain.model.person;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum TypeEnum implements CommonEnum {
	N("职能部室"),
	P("项目部");

	private String name;
	
	private TypeEnum(String value){
		this.name=value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
