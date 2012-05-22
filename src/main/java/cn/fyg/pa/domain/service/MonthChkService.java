package cn.fyg.pa.domain.service;

import java.util.List;

import cn.fyg.pa.domain.common.StateChangeException;
import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.person.Person;

public interface MonthChkService {
	
	MonthChk find(Long id);
	
	MonthChk save(MonthChk monthChk);
	
	MonthChk getCurrentMonthChk(Person person);
	
	List<MonthChk> getMonthChkByPeriodAndDepartmentAndState(Long year,Long month,String department,MonthChkEnum... states);
	
	List<MonthChk> getMonthChkByDepartmentAndState(String department,MonthChkEnum... states);
	
	List<MonthChk> getMonthChkByPersonAndState(Long year, Person person,MonthChkEnum... states);
	
	MonthChk next(Long id) throws StateChangeException;
	
	MonthChk back(Long id) throws StateChangeException;

	
}
