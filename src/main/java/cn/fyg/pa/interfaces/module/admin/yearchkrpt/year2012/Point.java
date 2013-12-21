package cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2012;

import java.math.BigDecimal;

import cn.fyg.pa.interfaces.module.admin.yearchkrpt.PointItem;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

public class Point  implements PointItem{
	
	//人员id
	private Long personId;
	//人员名称
	private String personName;
	//人员部门
	private String personDept;
	//分级考核得分
	private BigDecimal scheck=null; 
	//部门考核平均值
	private BigDecimal mdep=null;
	//总体考核平均值
	private BigDecimal mall=null;
	//部门考核幅度
	private BigDecimal damp=null;
	//考核幅度平均值
	private BigDecimal mamp=null;
	//分级考核总分
	private BigDecimal stotal=new BigDecimal("150");
	//分级均衡得分值
	private BigDecimal s=null;
	
	
	//分级考核幅度
	private BigDecimal alpha=null;
	//最高得分
	private BigDecimal maxs=null;
	//最低得分
	private BigDecimal mins=null;
	//分级均衡得分幅度
	private BigDecimal samp=null;
	//s平均分
	private BigDecimal savg=null;
	//纵向考核最后得分
	private BigDecimal upsilon=null;
	
	
	//横向考核得分
	private BigDecimal val=null;
	//最终排名
	private int ranking=0;
	//最终得分
	private BigDecimal result=null;
	
	/**
	 * 根据提供的值，计算s 公式如下
	 * [Scheck+(Mall-Mdep)]/Stotal
	 *					          
	 * 个人得分   + ( 员工平均得分 - 部门平均分 )			
	 * -----------------------------------
	 * 				总分
	 */
	public void calculatS(){
		if(damp.compareTo(Constant.ZERO)==0) return;
		
		BigDecimal up=scheck.subtract(mdep).add(mall);
		BigDecimal down=stotal;
		s=up.divide(down,Constant.SCALE,Constant.ROUND_MODEL);
	}
	
	/**
	 * 计算upsilon
	 * 分级平均考核幅度 alpha=Mamp/Stotal;
	 * upsilon=(S-Savg)*alpha*100
	 */
	public void calculatUpsilon(){
		if(samp.compareTo(Constant.ZERO)==0) return;
		
		alpha=mamp.divide(stotal, Constant.SCALE, Constant.ROUND_MODEL);
		upsilon=s.subtract(savg).multiply(alpha).multiply(Constant.HUNDRED).setScale(Constant.SCALE, Constant.ROUND_MODEL);
	}
	
	/**
	 * 计算合计值
	 * Result=upsilon+val
	 */
	public void calculatResult(){
		val=val.setScale(Constant.SCALE,Constant.ROUND_MODEL);
		result=upsilon.add(val);
	}
	
	public Point personId(Long personId) {
		this.personId = personId;
		return this;
	}
	
	public Point personName(String personName) {
		this.personName = personName;
		return this;
	}
	
	public Point personDept(String personDept) {
		this.personDept = personDept;
		return this;
	}
	
	public Point scheck(BigDecimal scheck) {
		this.scheck = scheck;
		return this;
	}
	
	public Point mdep(BigDecimal mdep) {
		this.mdep = mdep;
		return this;
	}
	
	public Point mall(BigDecimal mall) {
		this.mall = mall;
		return this;
	}
	
	public Point damp(BigDecimal damp) {
		this.damp = damp;
		return this;
	}
	
	public Point s(BigDecimal s) {
		this.s = s;
		return this;
	}
	
	public Point mamp(BigDecimal mamp) {
		this.mamp = mamp;
		return this;
	}
	
	public Point maxs(BigDecimal maxs) {
		this.maxs = maxs;
		return this;
	}
	
	public Point upsilon(BigDecimal upsilon) {
		this.upsilon = upsilon;
		return this;
	}
	
	public Point alpha(BigDecimal alpha) {
		this.alpha = alpha;
		return this;
	}
	
	public Point samp(BigDecimal samp) {
		this.samp = samp;
		return this;
	}
	
	public Point stotal(BigDecimal stotal) {
		this.stotal = stotal;
		return this;
	}
	
	public Point mins(BigDecimal mins) {
		this.mins = mins;
		return this;
	}
	
	public Point val(BigDecimal val) {
		this.val = val;
		return this;
	}

	public Point result(BigDecimal result) {
		this.result = result;
		return this;
	}

	public Point ranking(int ranking) {
		this.ranking = ranking;
		return this;
	}
	
	public Point savg(BigDecimal savg){
		this.savg=savg;
		return this;
	}

	public int getRanking() {
		return ranking;
	}

	public Long getPersonId() {
		return personId;
	}
	
	public String getPersonName() {
		return personName;
	}
	
	public String getPersonDept() {
		return personDept;
	}

	public BigDecimal getScheck() {
		return scheck;
	}

	public BigDecimal getMdep() {
		return mdep;
	}

	public BigDecimal getMall() {
		return mall;
	}

	public BigDecimal getDamp() {
		return damp;
	}
	
	public BigDecimal getMamp() {
		return mamp;
	}
	
	public BigDecimal getStotal() {
		return stotal;
	}
	public BigDecimal getS() {
		return s;
	}

	public BigDecimal getAlpha() {
		return alpha;
	}
	public BigDecimal getMaxs() {
		return maxs;
	}
	
	public BigDecimal getMins() {
		return mins;
	}
	
	public BigDecimal getSamp() {
		return samp;
	}
	public BigDecimal getUpsilon() {
		return upsilon;
	}

	public BigDecimal getVal() {
		return val;
	}

	public BigDecimal getResult() {
		return result;
	}

	public BigDecimal getSavg() {
		return savg;
	}

	@Override
	public void setRanking(int i) {
		this.ranking=i;
	}

	
	
	
	
}
