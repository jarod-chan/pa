package cn.fyg.pa.interfaces.module.atten.busiout.flow;

import java.math.BigDecimal;

import cn.fyg.pa.domain.shared.CommonEnum;

public enum RangeEnum implements CommonEnum {
	Tmanage("总经理",4),
	Gmanage("副总经理",3),
	Dmanage("部门经理",2),
	Staff("员工",1);
	
	private String name;
	private int level;

	RangeEnum(String name,int level){
		this.name=name;
		this.level=level;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}
	
	public static RangeEnum requireLevel(BigDecimal outDays){
		// 0<outDays<=1 
		if(outDays.compareTo(new BigDecimal("0"))>0 && outDays.compareTo(new BigDecimal("1"))<=0){
			return Dmanage;
		};
		//1<outDays<=3
		if(outDays.compareTo(new BigDecimal("0"))>1 && outDays.compareTo(new BigDecimal("3"))<=0){
			return Gmanage;
		}
		//outDays>3
		if(outDays.compareTo(new BigDecimal("3"))>0){
			return Tmanage;
		}
		return Staff;
	}
	
	/**
	 * 比较两个范围的大小
	 * @param o
	 * @return
	 */
	public int compareLevel(RangeEnum o) {
		RangeEnum other = o;
		RangeEnum self = this;
		return self.level-other.level;
	}

}
