package cn.fyg.pa.interfaces.web.module.admin.yearchkrpt;

import java.math.BigDecimal;

public interface PointItem {
	
	
	/**
	 * 设置名次
	 * @param i
	 */
	void setRanking(int i);
	
	/**
	 * 最后得分
	 * @return
	 */
	BigDecimal getResult();

}
