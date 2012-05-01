package cn.fyg.pa.domain.yearchk;

public class YearConfigFactory {
	
	public static YearConfig createYearConfig(Long Year){
		YearConfig  yearConfig=new YearConfig();
		yearConfig.setYear(Year);
		yearConfig.setEnable(true);
		return yearConfig;
	}

}
