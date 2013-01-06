package cn.fyg.pa.domain.model.yearchk;

import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.person.Person;

public interface YearChkRepositroy {

	
	List<Fycheck> getPersonYearChkAboutPerson(Long year,Person aboutPerson,Person chkPerson);
	
	void saveFychecks(List<Fycheck> fychecks);

	List<Fycheck> getPersonYearChkByChkperson(Long year,Person person);

	Map<Long, Object[]> getPersonYearChkResult(Long year, Person person);

}
