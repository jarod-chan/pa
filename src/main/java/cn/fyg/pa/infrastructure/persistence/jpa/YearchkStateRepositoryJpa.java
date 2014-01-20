package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.yearchkstate.YearchkState;
import cn.fyg.pa.domain.model.yearchkstate.YearchkStateRepository;

@Repository
public class YearchkStateRepositoryJpa implements YearchkStateRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public YearchkState save(YearchkState yearchkState) {
		if(yearchkState.getId()==null){
			entityManager.persist(yearchkState);
			return yearchkState;
		}
		return entityManager.merge(yearchkState);
	}

	@Override
	public YearchkState findByYearAndPersonId(Long year, Long personId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<YearchkState> query = builder.createQuery(YearchkState.class);
		Root<YearchkState> root = query.from(YearchkState.class);
		Predicate predicate = builder.equal(root.get("year"), year);
		predicate=builder.and(predicate,builder.equal(root.get("personId"), personId));
		query.where(predicate);
		List<YearchkState> resultList = entityManager.createQuery(query).getResultList();
		return resultList.isEmpty()?null:resultList.get(0);
	}

}
