package cn.fyg.pa.domain.model.yearchk;

import java.util.List;

public interface YearConfigRepositroy {
	
	YearConfig getEnableYear()throws EnableYearNotExist;
	
	YearConfig find(Long year);

	YearConfig save(YearConfig yearConfig);
	
	List<YearConfig> findAll();

}
