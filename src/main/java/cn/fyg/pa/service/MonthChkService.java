package cn.fyg.pa.service;

import java.util.List;

import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.StateChangeException;
import cn.fyg.pa.model.enums.MonthChkEnum;

public interface MonthChkService {
	
	MonthChk find(Long id);
	
	MonthChk save(MonthChk monthChk);
	
	MonthChk getCurrentMonthChk(Person person);
	
	List<MonthChk> getMonthChkByPeriodAndState(Long year,Long month,String department,MonthChkEnum... states);
	
	List<MonthChk> getMonthChkByDepartmentAndState(String department,MonthChkEnum... states);
	
	List<MonthChk> getAllFinishMonthChkByPerson(Person person);
	
	MonthChk next(Long id) throws StateChangeException;
	
	MonthChk back(Long id) throws StateChangeException;
}
