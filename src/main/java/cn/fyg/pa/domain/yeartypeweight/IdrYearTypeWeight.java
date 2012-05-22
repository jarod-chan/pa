package cn.fyg.pa.domain.yeartypeweight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import cn.fyg.pa.domain.common.Result;

//年度类别权重，非数据库实体
@Entity
public class IdrYearTypeWeight {
	
	@Id
	private Long year;
	
	@OneToMany(mappedBy = "idrYearTypeWeight",
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = IdrTypeWeight.class)
	@OrderBy("sn ASC")
	private List<IdrTypeWeight> idrTypeWeight=new ArrayList<IdrTypeWeight>();
	
	public Result isTypeWeightRight() {
		BigDecimal total = new BigDecimal(0);
		BigDecimal moreThis = new BigDecimal(0);
		BigDecimal lessThis = new BigDecimal(1);
		Set<Long> idrTypeId = new HashSet<Long>();
		if (this.idrTypeWeight.size() == 0) {
			return new Result(false, "类别不能为空");
		}
		for (IdrTypeWeight idrTypeWeight : this.idrTypeWeight) {
			BigDecimal typeWeight = idrTypeWeight.getWeight();
			if (typeWeight == null)
				return new Result(false, "权重不能为空");
			if (!(typeWeight.compareTo(moreThis) > 0 && typeWeight
					.compareTo(lessThis) < 0)) {
				return new Result(false, "权重只能在0和1之间");
			}
			total = total.add(typeWeight);
			idrTypeId.add(idrTypeWeight.getIdrType().getId());
		}
		if (total.compareTo(lessThis) != 0) {
			return new Result(false, "权重之和必须为1");
		}
		if (idrTypeId.size() != this.idrTypeWeight.size()) {
			return new Result(false, "权重类别不能重复");
		}

		return new Result().pass(true);
	}
	
	public Long getYear() {
		return year;
	}
	
	public void setYear(Long year) {
		this.year = year;
	}
	
	public List<IdrTypeWeight> getIdrTypeWeight() {
		return idrTypeWeight;
	}
	
	public void setIdrTypeWeight(List<IdrTypeWeight> idrTypeWeight) {
		this.idrTypeWeight = idrTypeWeight;
	}
	
	
}
