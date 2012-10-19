package cn.fyg.pa.domain.model.questionaires.ques;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum QuesState implements CommonEnum {
	notstarted("未开始"),
	active("执行中"),
	finish("已完成")
	;
	
	private String name;
	
	private QuesState(String name) {
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
