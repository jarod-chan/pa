package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.model.atten.busiout.Busiout;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.Result;

public interface BusioutService {
	
	Busiout create(Person person);
	
	Result verify(Busiout busiout);

	String getNextNo(Person person,Long year,Long month);
	
	Busiout save(Busiout busiout);
	
	Busiout find(Long id);
	
	List<Busiout> getBusioutByPersonAndYearAndMonth(Person person,Long year,Long month);
}
