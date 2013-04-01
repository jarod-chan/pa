package cn.fyg.pa.domain.model.atten.opinion;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum ResultEnum implements CommonEnum {
	
	agree("同意","agree"),
	disagree("不同意","disagree");
	
	private String name;
	
	private String val;//流程值，用来控制流程分支
	
	private ResultEnum(String name,String val){
		this.name=name;
		this.val=val;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

	public String val() {
		return this.val;
	}
	
	public static List<ResultEnum> agreeItems(){
		List<ResultEnum> items=new ArrayList<ResultEnum>();
		items.add(ResultEnum.agree);
		items.add(ResultEnum.disagree);
		return items;
	}
	

}
