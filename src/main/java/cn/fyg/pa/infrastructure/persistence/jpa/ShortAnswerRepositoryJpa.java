package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.questionaires.problem.ShortAnswer;
import cn.fyg.pa.domain.model.questionaires.problem.ShortAnswerRepository;

@Repository
public class ShortAnswerRepositoryJpa implements ShortAnswerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ShortAnswer> findByQtidAndPartid(Long qtid, Long partid) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ShortAnswer> query = builder.createQuery(ShortAnswer.class);
		Root<ShortAnswer> from = query.from(ShortAnswer.class);
		query.where(builder.equal(from.get("qtid"),qtid));
		query.where(builder.equal(from.get("partid"),partid));
		query.orderBy(builder.asc(from.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

}
