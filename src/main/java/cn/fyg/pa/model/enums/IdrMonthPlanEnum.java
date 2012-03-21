package cn.fyg.pa.model.enums;

public enum IdrMonthPlanEnum implements CommonEnum{
	SAVED("暂存"),
	SUBMITTED("已提交"),
	AUTDITED("已审核"),
	FINISHED("已完成");

	private String name;
	private IdrMonthPlanEnum(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
