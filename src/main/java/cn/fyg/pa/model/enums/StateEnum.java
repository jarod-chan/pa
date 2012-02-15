package cn.fyg.pa.model.enums;

public enum StateEnum implements CommonEnum {
	SAVED("暂存"),
	SUBMITTED("已提交"),
	FINISHED("已完成");
	
	private String name;
	
	private StateEnum(String name){
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
