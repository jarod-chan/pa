package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.summary.PersonSummary;
import cn.fyg.pa.domain.model.summary.PersonSummaryRepository;

@Repository
public class PersonSummaryRepositoryJpa implements PersonSummaryRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PersonSummary save(PersonSummary personSummary) {
		if(personSummary.getId()==null){
			 entityManager.persist(personSummary);
			 return personSummary;
		}
		return entityManager.merge(personSummary);
	}

	@Override
	public PersonSummary findByYearAndPersonId(Long year, Long personId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonSummary> query = builder.createQuery(PersonSummary.class);
		Root<PersonSummary> from = query.from(PersonSummary.class);
		query.where(
				builder.and(
						builder.equal(from.get("year"), year)),
						builder.equal(from.get("personId"), personId)
		);
		List<PersonSummary> resultList = entityManager.createQuery(query).getResultList();
		return resultList.isEmpty()?null:resultList.get(0);
	}

}
