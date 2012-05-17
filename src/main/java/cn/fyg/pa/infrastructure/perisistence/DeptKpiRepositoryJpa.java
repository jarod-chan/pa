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
import cn.fyg.pa.domain.deptkpi.DeptKpi;
import cn.fyg.pa.domain.deptkpi.DeptKpiRepository;

@Repository
public class DeptKpiRepositoryJpa implements DeptKpiRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public DeptKpi findByYearAndDepartment(Long year, Department department) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeptKpi> query = builder.createQuery(DeptKpi.class);
		Root<DeptKpi> from = query.from(DeptKpi.class);
		Predicate criteria=builder.and(
				builder.equal(from.get("year"), year),
				builder.equal(from.get("department"), department)
				);
		query.where(criteria);
		List<DeptKpi> resultList = entityManager.createQuery(query).getResultList();
		return resultList.isEmpty()?null:resultList.get(0);
	}

}
