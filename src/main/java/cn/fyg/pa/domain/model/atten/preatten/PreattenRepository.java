package cn.fyg.pa.domain.model.atten.preatten;

import java.util.List;

import cn.fyg.pa.domain.model.person.Person;

public interface PreattenRepository {

	String getMaxNo(Person person, Long year, Long month);

	Preatten save(Preatten preatten);

	Preatten find(Long id);

	List<Preatten> findByPersonAndYearAndMonth(Person person, Long year,Long month);

}
