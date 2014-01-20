package cn.fyg.pa.domain.model.monthchk;

import java.util.List;

import cn.fyg.pa.domain.model.person.Person;


public interface MonthChkRepository {
	
	MonthChk find(Long id);
	
	MonthChk save(MonthChk monthChk);
	
	MonthChk findLastMonthMonthChk(Person person);

	List<MonthChk> findMonthChkByPeriod(Long year,Long month);
	
	List<MonthChk> getMonthChkByPeriodAndDepartmentAndState(Long year,Long month,String department,MonthChkEnum... states);
	
	List<MonthChk> getMonthChkByDepartmentAndState(String department,MonthChkEnum... states);
	
	List<MonthChk> getMonthChkByPersonAndState(Long year, Person person,MonthChkEnum... states);

	
}