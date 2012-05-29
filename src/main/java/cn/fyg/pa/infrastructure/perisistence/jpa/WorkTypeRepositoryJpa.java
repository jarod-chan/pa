package cn.fyg.pa.infrastructure.perisistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.worktype.WorkType;
import cn.fyg.pa.domain.model.worktype.WorkTypeRepository;


@Repository
public class WorkTypeRepositoryJpa implements WorkTypeRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<WorkType> findAllWorkTypes() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<WorkType> query = builder.createQuery(WorkType.class);
		Root<WorkType> from = query.from(WorkType.class);
		query.orderBy(builder.asc(from.get("sn")));
		return entityManager.createQuery(query).getResultList();
	}

}
