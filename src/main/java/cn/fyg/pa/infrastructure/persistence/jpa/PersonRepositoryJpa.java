package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.person.ManageEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.person.StateEnum;
import cn.fyg.pa.domain.model.person.TypeEnum;

@Service
public class PersonRepositoryJpa implements PersonRepository {
		
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Person find(Long id) {
		return entityManager.find(Person.class, id);
	}

	@Override
	public Person save(Person person) {
		if (person.getId() == null) {
			entityManager.persist(person);
			return person;
		} else {
			return entityManager.merge(person);
		}
	}

	@Override
	public Person findByName(String personname){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query = builder.createQuery(Person.class);
		Root<Person> root = query.from(Person.class);
		Predicate criteria=builder.equal(root.get("name"), personname);
		criteria=builder.or(criteria,builder.equal(root.get("email"), personname));
		criteria=builder.or(criteria,builder.equal(root.get("email"), personname+"@fyg.cn"));
		query.where(criteria);
		query.orderBy(builder.asc(root.get("id")));
		List<Person> retList=entityManager.createQuery(query).setMaxResults(1).getResultList();
		return retList.isEmpty()?null:retList.get(0);
	}

	@Override
	public Person findDepartmentMange(String department) {
		String sql="select p from Person p where p.department=:department and p.manage=:manage";
		List<Person> ret=entityManager.createQuery(sql,Person.class)
				.setParameter("department", department)
				.setParameter("manage", ManageEnum.Y)
				.setMaxResults(1)
				.getResultList();
		return ret.isEmpty()?null:ret.get(0);
	}
	
	@Override
	public List<Person> findPersonByManageOrderByDepartment(ManageEnum... mangeEnum) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query = builder.createQuery(Person.class);
		Root<Person> person = query.from(Person.class);
		Root<Department> department=query.from(Department.class);

		Predicate criteria=person.get("manage").in((Object[])mangeEnum);
		criteria=builder.and(builder.equal(person.get("department"), department.get("name")),criteria);
		query.select(person);
		query.where(criteria);
		query.orderBy(builder.asc(department.get("number")));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public int countStaffByType(TypeEnum type) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query=builder.createQuery(Long.class);
		Root<Person> root=query.from(Person.class);
		Predicate criteria=builder.equal(root.get("type"), type);
		criteria=builder.and(criteria,builder.equal(root.get("manage"), ManageEnum.N));
		criteria=builder.and(criteria,builder.equal(root.get("state"), StateEnum.valid));
		query.select(builder.count(root.get("id")));
		query.where(criteria);
		return entityManager.createQuery(query).getSingleResult().intValue();
	}
	
	
	@Override
	public List<Person>  getStaffByType(TypeEnum type){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query=builder.createQuery(Person.class);
		Root<Person> root=query.from(Person.class);
		Predicate criteria=builder.equal(root.get("type"), type);
		criteria=builder.and(criteria,builder.equal(root.get("manage"), ManageEnum.N));
		query.where(criteria);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public List<Person> getStaffByTypeValid(TypeEnum type) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query=builder.createQuery(Person.class);
		Root<Person> root=query.from(Person.class);
		Predicate criteria=builder.equal(root.get("type"), type);
		criteria=builder.and(criteria,builder.equal(root.get("manage"), ManageEnum.N));
		criteria=builder.and(criteria,builder.equal(root.get("state"), StateEnum.valid));
		query.where(criteria);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public List<Person> getStaffByDept(String department){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query=builder.createQuery(Person.class);
		Root<Person> root=query.from(Person.class);
		Predicate criteria=builder.equal(root.get("department"), department);
		criteria=builder.and(criteria,builder.equal(root.get("manage"), ManageEnum.N));
		query.where(criteria);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public List<Person> getStaffByDeptValid(String department) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query=builder.createQuery(Person.class);
		Root<Person> root=query.from(Person.class);
		Predicate criteria=builder.equal(root.get("department"), department);
		criteria=builder.and(criteria,builder.equal(root.get("manage"), ManageEnum.N));
		criteria=builder.and(criteria,builder.equal(root.get("state"), StateEnum.valid));
		query.where(criteria);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}


	@Override
	public List<Person> getAllFyperson() {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query=builder.createQuery(Person.class);
		Root<Person> root=query.from(Person.class);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void remove(Person person) {
		entityManager.remove(person);
	}

	@Override
	public void saveAll(List<Person> persons) {
		for (Person fyperson : persons) {
			if (fyperson.getId() == null) {
				entityManager.persist(fyperson);
			} else {
				entityManager.merge(fyperson);
			}	
		}
	}

	@Override
	public List<Person> findPersonByManageOrderById(ManageEnum... mangeEnum) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query = builder.createQuery(Person.class);
		Root<Person> person = query.from(Person.class);

		Predicate criteria=person.get("manage").in((Object[])mangeEnum);
		query.select(person);
		query.where(criteria);
		query.orderBy(builder.asc(person.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

}
