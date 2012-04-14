package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.MonthChkRepository;
import cn.fyg.pa.domain.model.MonthChk;

@Repository
public class MonthChkRepositoryImp implements MonthChkRepository {
	
	@Resource
	MonthChkDao monthChkDao;
	

	@Override
	public List<MonthChk> getEveryoneMonthChkByPeriod(Long year,Long month){
		return monthChkDao.findByPeriodAndDepartmentAndPersonAndState(year, month, null, null);
	}

}
