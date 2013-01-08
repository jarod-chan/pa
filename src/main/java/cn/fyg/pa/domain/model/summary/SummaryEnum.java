package cn.fyg.pa.domain.model.summary;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum SummaryEnum implements CommonEnum  {
	
	save("已保存"),
	commit("已提交");

	private String name;
	
	private SummaryEnum(String name) {
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
