package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.model.atten.preatten.Preatten;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.Result;

public interface PreattenService {
	
	Preatten create(Person person);
	
	Result verify(Preatten preatten);
	
	String getNextNo(Person person,Long year,Long month);
	
	Preatten save(Preatten preatten);
	
	Preatten find(Long id);
	
	List<Preatten> getBusioutByPersonAndYearAndMonth(Person person,Long year,Long month);
}
