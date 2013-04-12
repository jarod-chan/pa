package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.PersonService;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Resource
	PersonRepository personRepository;
	
	@Override
	@Transactional
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	@Transactional
	public void remove(Long personId) {
		Person person = personRepository.find(personId);
		personRepository.remove(person);
	}

	@Override
	@Transactional
	public void saveAll(List<Person> persons) {
		personRepository.saveAll(persons);
	}

	@Override
	public Person find(Long id) {
		return personRepository.find(id);
	}

}
