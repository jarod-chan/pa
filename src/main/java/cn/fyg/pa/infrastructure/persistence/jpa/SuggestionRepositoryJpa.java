package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.suggestion.Suggestion;
import cn.fyg.pa.domain.model.suggestion.SuggestionRepository;

@Repository
public class SuggestionRepositoryJpa implements SuggestionRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Suggestion save(Suggestion suggestion) {
		if(suggestion.getId()==null){
			entityManager.persist(suggestion);
			return suggestion;
		}
		return entityManager.merge(suggestion);
	}

	@Override
	public List<Suggestion> findByPersonId(Long personId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Suggestion> query = builder.createQuery(Suggestion.class);
		Root<Suggestion> from = query.from(Suggestion.class);
		query.where(builder.equal(from.get("personId"),personId));
		query.orderBy(builder.desc(from.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void delete(Long id) {
		Suggestion suggestion = this.entityManager.find(Suggestion.class, id);
		this.entityManager.remove(suggestion);
	}

}
