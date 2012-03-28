package cn.fyg.pa.service;

import java.util.List;

import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.enums.StateEnum;

public interface MonthChkService {
	
	List<MonthChk> getMonthChkByPeriodAndState(Long year,Long month,String department,StateEnum... states);

}
