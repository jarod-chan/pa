package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.atten.preatten.Preatten;
import cn.fyg.pa.domain.model.atten.preatten.PreattenRepository;
import cn.fyg.pa.domain.model.person.Person;

@Repository
public class PreattenRepositoryJpa implements PreattenRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public String getMaxNo(Person person, Long year, Long month) {
		String jpql="select a.no from Preatten a "+
				"where a.id=(select max(b.id) from Preatten b where b.person=:person and b.dayitem.year=:year and b.dayitem.month=:month )";
		List<String> resultList = entityManager.createQuery(jpql, String.class)
			.setParameter("person", person)
			.setParameter("year", year)
			.setParameter("month", month)
			.getResultList();
		return resultList.isEmpty()?null:resultList.get(0);
	}

	@Override
	public Preatten save(Preatten preatten) {
		if(preatten.getId()==null){
			entityManager.persist(preatten);
			return preatten;
		}
		return entityManager.merge(preatten);
	}

	@Override
	public Preatten find(Long id) {
		return entityManager.find(Preatten.class, id);
	}

	@Override
	public List<Preatten> findByPersonAndYearAndMonth(Person person, Long year,Long month) {
		String jpql="select p from Preatten p "+
				"where p.person=:person and p.dayitem.year=:year and p.dayitem.month=:month "+
				"order by p.id";
		List<Preatten> resultList = entityManager.createQuery(jpql, Preatten.class)
			.setParameter("person", person)
			.setParameter("year", year)
			.setParameter("month", month)
			.getResultList();
		return resultList;	
	}

}
