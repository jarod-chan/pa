package cn.fyg.pa.domain;

import java.util.List;

import cn.fyg.pa.domain.model.MonthChk;

public interface MonthChkRepository {

	List<MonthChk> getEveryoneMonthChkByPeriod(Long year,Long month);

}