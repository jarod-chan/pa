package cn.fyg.pa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.fyg.pa.domain.person.Person;


@Component
@Scope("prototype")
public class DataInitializer {

	public static final int PERSON_COUNT = 3;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Long> people = new ArrayList<Long>();

	public void initData() {
		people.clear();// clear out the previous list of people
		addPerson(1L,"john","j");
		addPerson(2L,"woo","w");
		addPerson(3L,"chan","c");
		entityManager.flush();
		entityManager.clear();
	}

	public void addPerson(Long id,String name, String chkstr) {
		Person p = new Person();
		p.setId(id);
		p.setName(name);
		p.setChkstr(chkstr);
		entityManager.persist(p);
		people.add(p.getId());
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
