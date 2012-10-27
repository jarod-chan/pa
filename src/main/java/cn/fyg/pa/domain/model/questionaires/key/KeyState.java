package cn.fyg.pa.domain.model.questionaires.key;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum KeyState implements CommonEnum {
	nouse("未使用"),
	used("已使用"),
	finish("已完成"),
	invalid("无效");
	
	private String name;
	
	private KeyState(String name){
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
