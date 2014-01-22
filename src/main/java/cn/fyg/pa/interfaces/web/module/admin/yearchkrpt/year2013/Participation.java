package cn.fyg.pa.interfaces.web.module.admin.yearchkrpt.year2013;

import java.math.BigDecimal;

/**
 * 绩效考核参与度
 */
public class Participation {

	// 横向考核平均分 sum(val)/总人数
	private BigDecimal Vavg = null;
	
	// 公司平均参与度
	private BigDecimal Iavg = null;
	
	// 员工绝对参与度
	private BigDecimal Iabs = null;
	
	// 有向绝对参与度=员工绝对参与度-公司平均参与度
	private BigDecimal Idir = null;
	
	// 加权系数 固定参数0.2
	private BigDecimal Ibeta = new BigDecimal("0.2");

	// 加权得分=横向考核平均分*有向绝对参与度*加权系数
	private BigDecimal Is = new BigDecimal("0");
	
	/**
	 * 加权得分=横向考核平均分*有向绝对参与度*加权系数
	 */
	public void calculat(){
		this.Idir=this.Iabs.subtract(this.Iavg);
		this.Is=this.Vavg.multiply(this.Idir).multiply(this.Ibeta);
	}

	public BigDecimal getVavg() {
		return Vavg;
	}

	public void setVavg(BigDecimal vavg) {
		Vavg = vavg;
	}

	public BigDecimal getIavg() {
		return Iavg;
	}

	public void setIavg(BigDecimal iavg) {
		Iavg = iavg;
	}

	public BigDecimal getIabs() {
		return Iabs;
	}

	public void setIabs(BigDecimal iabs) {
		Iabs = iabs;
	}

	public BigDecimal getIdir() {
		return Idir;
	}

	public void setIdir(BigDecimal idir) {
		Idir = idir;
	}

	public BigDecimal getIbeta() {
		return Ibeta;
	}

	public void setIbeta(BigDecimal ibeta) {
		Ibeta = ibeta;
	}

	public BigDecimal getIs() {
		return Is;
	}

	public void setIs(BigDecimal is) {
		Is = is;
	}

}
