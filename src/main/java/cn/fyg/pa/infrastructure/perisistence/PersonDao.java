package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.domain.person.ManageEnum;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.person.TypeEnum;


@Repository
public class PersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Person find(Long id) {
		return entityManager.find(Person.class, id);
	}
	
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
	
	public List<Person> findByManage(ManageEnum... mangeEnum){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query = builder.createQuery(Person.class);
		Root<Person> root = query.from(Person.class);
		Predicate criteria=root.get("manage").in((Object[])mangeEnum);
		query.where(criteria);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}
	
	public int countStaffByType(TypeEnum type) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query=builder.createQuery(Long.class);
		Root<Person> root=query.from(Person.class);
		Predicate criteria=builder.equal(root.get("type"), type);
		criteria=builder.and(criteria,builder.equal(root.get("manage"), ManageEnum.N));
		query.select(builder.count(root.get("id")));
		query.where(criteria);
		return entityManager.createQuery(query).getSingleResult().intValue();
	}

	public List<Person> getStaffByType(TypeEnum type) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> query=builder.createQuery(Person.class);
		Root<Person> root=query.from(Person.class);
		Predicate criteria=builder.equal(root.get("type"), type);
		criteria=builder.and(criteria,builder.equal(root.get("manage"), ManageEnum.N));
		query.where(criteria);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * 通过部门名称查找项目经理
	 * @param department
	 * @return
	 */
	public Person findDeptMange(String department){
		String sql="select p from fyperson p where p.department=:department and p.manage=:manage";
		List<Person> ret=entityManager.createQuery(sql,Person.class)
				.setParameter("department", department)
				.setParameter("manage", ManageEnum.Y)
				.setMaxResults(1)
				.getResultList();
		return ret.isEmpty()?null:ret.get(0);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Person> getAllFyperson() {
		Object obj= entityManager.createQuery("select p from fyperson p order by p.id asc").getResultList();
		return (List<Person>) obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getAllFypersonSameTypeAndNotSelfAndNotManage(Long id,TypeEnum type) {
		return entityManager.createQuery("select p from fyperson p where p.id!=:id and type=:type and manage='N' order by p.id asc")
				.setParameter("id",id)
				.setParameter("type",type)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getAllFypersonSameDepartmentAndNotSelf(Long id,String department) {
		return entityManager.createQuery("select p from fyperson p where p.id!=:id and department=:department order by p.id asc")
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

	public Person getMailAdressByUsername(String username) {
		String sql="select p from fyperson p where p.name=:name ";
		List<Person> ret=entityManager.createQuery(sql,Person.class)
				.setParameter("name", username)
				.setMaxResults(1)
				.getResultList();
		return ret.isEmpty()?null:ret.get(0);
	}
	
	
}
