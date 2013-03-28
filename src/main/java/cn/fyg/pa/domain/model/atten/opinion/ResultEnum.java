package cn.fyg.pa.domain.model.atten.opinion;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum ResultEnum implements CommonEnum {
	
	agree("同意","agree"),
	disagree("不同意","disagree");
	
	private String name;
	
	private Object val;//流程值，用来控制流程分支
	
	private ResultEnum(String name,Object val){
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

	public <T>T val(Class<T> resultClass) {
		if(val==null) return null;
		@SuppressWarnings("unchecked")
		T ret=(T)resultClass;
		return ret;
	}
	
	public static List<ResultEnum> agreeItems(){
		List<ResultEnum> items=new ArrayList<ResultEnum>();
		items.add(ResultEnum.agree);
		items.add(ResultEnum.disagree);
		return items;
	}
	

}
