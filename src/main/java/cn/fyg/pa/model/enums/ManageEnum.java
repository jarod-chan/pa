package cn.fyg.pa.model.enums;

public enum ManageEnum implements CommonEnum {
	G("总经理"),
	Y("经理"),
	N("职员");
	
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
