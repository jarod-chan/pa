package cn.fyg.pa.application;

import cn.fyg.pa.domain.yearchk.EnableYearNotExist;

public interface YearConfigService {
	
	Long getEnableYear()throws EnableYearNotExist;
	
	void enableYear(Long year);
	
	void diableYear(Long year);

}
