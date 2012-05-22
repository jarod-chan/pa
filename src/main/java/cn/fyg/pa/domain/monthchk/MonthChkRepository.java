package cn.fyg.pa.domain.monthchk;

import java.util.List;


public interface MonthChkRepository {

	List<MonthChk> getEveryoneMonthChkByPeriod(Long year,Long month);

}