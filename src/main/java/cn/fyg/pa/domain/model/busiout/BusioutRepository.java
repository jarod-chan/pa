package cn.fyg.pa.domain.model.busiout;

import java.util.List;

import cn.fyg.pa.domain.model.person.Person;

public interface BusioutRepository {
	
	Busiout save(Busiout busiout);
	
	String getMaxNo(Person person,Long year,Long month);

	List<Busiout> getBusioutByPersonAndYearAndMonth(Person person, Long year,Long month);

	Busiout find(Long id);
	
}
