package cn.fyg.pa.domain;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.MonthChk;
import cn.fyg.pa.infrastructure.perisistence.MonthChkDao;

@Repository
public class MonthChkRepositoryImp implements MonthChkRepository {
	
	@Resource
	MonthChkDao monthChkDao;
	

	@Override
	public List<MonthChk> getEveryoneMonthChkByPeriod(Long year,Long month){
		return monthChkDao.findByPeriodAndDepartmentAndPersonAndState(year, month, null, null);
	}

}
