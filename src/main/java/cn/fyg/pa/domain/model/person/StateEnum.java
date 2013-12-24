package cn.fyg.pa.domain.model.person;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum StateEnum implements CommonEnum {
	valid("有效"),	//正常状态
	invalid("无效"),	//返聘部分功能不参与
	leave("离职");	//离职，无法进入系统但保留信息

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
