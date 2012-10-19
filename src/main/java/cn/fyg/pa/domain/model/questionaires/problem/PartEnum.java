package cn.fyg.pa.domain.model.questionaires.problem;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum PartEnum implements CommonEnum {
	choice("选择题"),
	answert("简答题");
	
	private String name;
	
	PartEnum(String name){
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
