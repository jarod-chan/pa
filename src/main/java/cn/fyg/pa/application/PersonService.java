package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.model.person.Person;

public interface PersonService {
	
	Person find(Long id);
	
	Person save(Person person);

	void remove(Long personId);

	void saveAll(List<Person> persons);

}
