package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptindicator.DeptIndicator;
import cn.fyg.pa.domain.deptindicator.DeptIndicatorRepository;

@Repository
public class DeptIndicatorRepositoryJpa implements DeptIndicatorRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public DeptIndicator find(Long id) {
		return entityManager.find(DeptIndicator.class, id);
	}

	@Override
	public DeptIndicator findByYearAndDepartment(Long year,
			Department department) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeptIndicator> query = builder.createQuery(DeptIndicator.class);
		Root<DeptIndicator> from = query.from(DeptIndicator.class);
		Predicate criteria=builder.equal(from.get("year"), year);
		criteria=builder.and(criteria,builder.equal(from.get("department"), department));
		query.where(criteria);
		List<DeptIndicator> returnList = entityManager.createQuery(query).getResultList();
		return returnList.isEmpty()?null:returnList.get(0);
	}

	@Override
	public DeptIndicator save(DeptIndicator deptIndicator) {
		if (deptIndicator.getId() == null) {
			entityManager.persist(deptIndicator);
			return deptIndicator;
		} else {
			return entityManager.merge(deptIndicator);
		}
	}

	@Override
	public List<DeptIndicator> findByYear(Long year) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeptIndicator> query = builder.createQuery(DeptIndicator.class);
		Root<DeptIndicator> from = query.from(DeptIndicator.class);
		Predicate criteria=builder.equal(from.get("year"), year);
		query.where(criteria);
		return entityManager.createQuery(query).getResultList();
	}

}
