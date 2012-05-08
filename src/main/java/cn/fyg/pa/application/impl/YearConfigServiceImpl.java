package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.YearConfigService;
import cn.fyg.pa.domain.yearchk.EnableYearNotExist;
import cn.fyg.pa.domain.yearchk.YearConfig;
import cn.fyg.pa.domain.yearchk.YearConfigFactory;
import cn.fyg.pa.domain.yearchk.YearConfigRepositroy;

@Service
public class YearConfigServiceImpl implements YearConfigService {
	
	@Resource
	YearConfigRepositroy yearConfigRepositroy;

	@Override
	public Long getEnableYear() throws EnableYearNotExist {
		YearConfig yearConfig= yearConfigRepositroy.getEnableYear();
		return yearConfig.getYear();
	}

	@Override
	@Transactional
	public void enableYear(Long year) {
		List<YearConfig> allYearConfigs=yearConfigRepositroy.findAll();
		for (YearConfig yearConfig : allYearConfigs) {
			yearConfig.disable();
		}
		YearConfig yearConfig=yearConfigRepositroy.find(year);
		if(yearConfig==null){
			yearConfig=YearConfigFactory.createYearConfig(year);
			yearConfig=yearConfigRepositroy.save(yearConfig);
		}
		yearConfig.enable();
	}

	@Override
	@Transactional
	public void diableYear(Long year) {
		YearConfig yearConfig=yearConfigRepositroy.find(year);
		yearConfig.disable();
	}

}
