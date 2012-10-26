package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.questionaires.part.Part;
import cn.fyg.pa.domain.model.questionaires.part.PartRepository;

@Repository
public class PartRepositoryJpa implements PartRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Part> findByQtid(Long qtid) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Part> query = builder.createQuery(Part.class);
		Root<Part> from = query.from(Part.class);
		query.where(builder.equal(from.get("qtid"),qtid));
		query.orderBy(builder.asc(from.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

}
