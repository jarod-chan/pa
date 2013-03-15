package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.domain.model.busiout.Busiout;
import cn.fyg.pa.domain.model.busiout.BusioutRepository;
import cn.fyg.pa.domain.model.person.Person;

@Repository
public class BusioutRepositoryJpa implements BusioutRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Busiout save(Busiout busiout) {
		if(busiout.getId()==null){
			entityManager.persist(busiout);
			return busiout;
		}
		return entityManager.merge(busiout);
	}

	@Override
	public String getMaxNo(Person person, Long year, Long month) {
		String jpql="select a.no from Busiout a " +
				"where a.id=(select max(b.id) from Busiout b where b.person=:person and b.year=:year and b.month=:month ) ";
		@SuppressWarnings("unchecked")
		List<String> resultList = entityManager.createQuery(jpql)
			.setParameter("person", person)
			.setParameter("year", year)
			.setParameter("month", month)
			.getResultList();
		if(resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

}
