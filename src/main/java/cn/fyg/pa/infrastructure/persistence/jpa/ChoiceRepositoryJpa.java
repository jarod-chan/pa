package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.questionaires.problem.Choice;
import cn.fyg.pa.domain.model.questionaires.problem.ChoiceRepository;

@Repository
public class ChoiceRepositoryJpa implements ChoiceRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Choice> findByQtidAndPartid(Long qtid, Long partid) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Choice> query = builder.createQuery(Choice.class);
		Root<Choice> from = query.from(Choice.class);
		query.where(builder.equal(from.get("qtid"),qtid));
		query.where(builder.equal(from.get("partid"),partid));
		query.orderBy(builder.asc(from.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

}
