package cn.fyg.pa.domain.companykpi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.apache.commons.lang.StringUtils;

import cn.fyg.pa.domain.model.IdrTypeWeight;
import cn.fyg.pa.domain.model.Result;


//年度指标
@Entity
public class IdrYearCompany {

	@Id
	private Long year;

	
	@OneToMany(mappedBy = "idrYearCompany",
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = IdrCompany.class)
	@OrderBy("sn ASC")
	private List<IdrCompany> idrCompany=new ArrayList<IdrCompany>();
	
	
	/**
	 * 按idrtypeweight排序 
	 */
	public void sortIdrCompanyByIdrTypeWeight(){
		Collections.sort(this.idrCompany, new IdrTypeWeightComparator());
		reIndex();
	}
	
	/**
	 * 重新排序
	 */
	private void reIndex(){
		for(int i=0,len=this.idrCompany.size();i<len;i++){
			this.idrCompany.get(i).setSn(1L+i);
		}
	}
	
	/**
	 * 按照类别的顺序排序
	 */
	private class IdrTypeWeightComparator implements Comparator<IdrCompany>{
		@Override
		public int compare(IdrCompany one, IdrCompany two) {
			if(one.getIdrTypeWeight().getSn().compareTo(two.getIdrTypeWeight().getSn())==0){
				return one.getSn().compareTo(two.getSn());
			}
			return one.getIdrTypeWeight().getSn().compareTo(two.getIdrTypeWeight().getSn());
		}
	}
	
	/**
	 * 校验年度kpi分解是否合法
	 * @return
	 */
	public Result verifySelf(){
		
		if(this.idrCompany.isEmpty()){
			return new Result(false,"kpi指标不能为空");
		}
		Result resultEveryOne=verifyEveryOneIdrCompany();
		if(resultEveryOne.notPass()){
			return resultEveryOne;
		}
		Result resultAll=verifyAllIdrCompany();
		if(resultAll.notPass()){
			return resultAll;
		}
		
		return new Result().pass(true);
	}

	/**
	 * 校验每个指标是否合法
	 * @return
	 */
	private Result verifyEveryOneIdrCompany() {
		BigDecimal moreThis=new BigDecimal(0);
		BigDecimal lessThis=new BigDecimal(1);
		for(IdrCompany idrCompany:this.idrCompany){
			if(StringUtils.isBlank(idrCompany.getNumber())){
				return new Result(false,"指标编码不能为空");
			}
			if(StringUtils.isBlank(idrCompany.getContext())){
				return new Result(false,"指标内容不能为空");
			}
			BigDecimal weight=idrCompany.getWeight();
			if(weight==null){
				return new Result(false,"指标权重不能为空");
			}
			if(!(weight.compareTo(moreThis)>0 && weight.compareTo(lessThis)<0)){
				return new Result(false,"指标权重只能介于0和1之间");
			}
		}
		return new Result().pass(true);
	}
	
	/**
	 * 校验指标项整体是否合法
	 * @return
	 */
	private Result verifyAllIdrCompany() {
		Map<Long,BigDecimal> typeWeights = getAllTypeWeights();
		BigDecimal total=new BigDecimal(0);
		BigDecimal target=new BigDecimal(1);
		for(BigDecimal typeWeight:typeWeights.values()){
			total=total.add(typeWeight);
		}
		if(total.compareTo(target)!=0){
			return new Result(false,"所有类别权重之和不为1");
		}
		
		Map<Long,BigDecimal> companyWeights=getCompanyWeights();
		for(BigDecimal sumTypeWeight:companyWeights.values()){
			if(sumTypeWeight.compareTo(target)!=0){
				return new Result(false,"同类别权重之和不为1");
			}
		}
		
		return new Result().pass(true);
	}

	private Map<Long,BigDecimal> getCompanyWeights() {
		Map<Long,BigDecimal> companyWeights=new HashMap<Long,BigDecimal>();
		for(IdrCompany idrCompany:this.getIdrCompany()){
			IdrTypeWeight idrTypeWeight=idrCompany.getIdrTypeWeight();
			Long idrTypeWeightId = idrTypeWeight.getId();
			BigDecimal weight=idrCompany.getWeight();
			if(!companyWeights.containsKey(idrTypeWeightId)){
				companyWeights.put(idrTypeWeightId, weight);
			}else{
				companyWeights.put(idrTypeWeightId, companyWeights.get(idrTypeWeightId).add(weight));
			}
		}
		return companyWeights;
	}

	private Map<Long, BigDecimal> getAllTypeWeights() {
		Map<Long,BigDecimal> typeWeights=new HashMap<Long,BigDecimal>();
		for(IdrCompany idrCompany:this.getIdrCompany()){
			IdrTypeWeight idrTypeWeight=idrCompany.getIdrTypeWeight();
			Long idrTypeWeightId = idrTypeWeight.getId();
			if(!typeWeights.containsKey(idrTypeWeightId)){
				typeWeights.put(idrTypeWeightId, idrTypeWeight.getWeight());
			}
		}
		return typeWeights;
	}
	
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public List<IdrCompany> getIdrCompany() {
		return idrCompany;
	}

	public void setIdrCompany(List<IdrCompany> idrCompany) {
		this.idrCompany = idrCompany;
	}


	
}
