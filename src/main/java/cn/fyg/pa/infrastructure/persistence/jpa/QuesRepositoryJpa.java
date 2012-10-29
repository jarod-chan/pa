package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.questionaires.ques.Ques;
import cn.fyg.pa.domain.model.questionaires.ques.QuesRepository;

@Repository
public class QuesRepositoryJpa implements QuesRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Ques find(Long id) {
		return entityManager.find(Ques.class, id);
	}

	@Override
	public List<Ques> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ques> query = builder.createQuery(Ques.class);
		Root<Ques> from = query.from(Ques.class);
		query.orderBy(builder.asc(from.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

}
