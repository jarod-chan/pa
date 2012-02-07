package cn.fyg.pa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.model.Person;


@Repository
public class PersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Person find(Long id) {
		return entityManager.find(Person.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Person findByName(String personname) {
		List<Person> ret=entityManager.createQuery("select p from Person p where p.name=:personname order by p.id asc").setParameter("personname",personname).getResultList();
		return ret.isEmpty()?null:(Person)ret.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getAllFyperson() {
		Object obj= entityManager.createQuery("select p from fyperson p order by p.id asc").getResultList();
		return (List<Person>) obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getAllFypersonSameTypeAndNotSelfAndNotManage(Long id,String type) {
		return entityManager.createQuery("select p from Person p where p.id!=:id and type=:type and manage='N' order by p.id asc")
				.setParameter("id",id)
				.setParameter("type",type)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getAllFypersonSameDepartmentAndNotSelf(Long id,String department) {
		return entityManager.createQuery("select p from Person p where p.id!=:id and department=:department order by p.id asc")
				.setParameter("id",id)
				.setParameter("department",department)
				.getResultList();
	}
	
	@Transactional
	public Person save(Person Fyperson) {
		if (Fyperson.getId() == null) {
			entityManager.persist(Fyperson);
			return Fyperson;
		} else {
			return entityManager.merge(Fyperson);
		}		
	}	
	
	@Transactional
	public Person persist(Person Fyperson) {
			entityManager.persist(Fyperson);
			return Fyperson;
	}	
	
	public void flush(){
		entityManager.flush();
	}
	
	public void refresh(Person person){
		entityManager.refresh(person);
	}
	
	@Transactional
	public Person merge(Person fyperosn) {
			Person person=entityManager.merge(fyperosn);
			return person;
	}	
	
	@Transactional
	public void saveAll(List<Person> list){
		for (Person fyperson : list) {
			if (fyperson.getId() == null) {
				entityManager.persist(fyperson);
			} else {
				entityManager.merge(fyperson);
			}	
		}
	}
	
	@Transactional
	public void remove(Long id){
		Person person=entityManager.find(Person.class, id);
		entityManager.remove(person);
	}
	
}
