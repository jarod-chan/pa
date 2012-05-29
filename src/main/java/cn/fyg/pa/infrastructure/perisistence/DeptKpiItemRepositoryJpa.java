package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.companykpi.IdrCompany;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItemRepository;

@Repository
public class DeptKpiItemRepositoryJpa implements DeptKpiItemRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public DeptKpiItem find(Long id) {
		return entityManager.find(DeptKpiItem.class, id);
	}

	@Override
	public DeptKpiItem save(DeptKpiItem deptKpiItem) {
		if(deptKpiItem.getId()==null){
			entityManager.persist(deptKpiItem);
			return deptKpiItem;
		}
		return entityManager.merge(deptKpiItem);
	}

	@Override
	public List<DeptKpiItem> findByYearAndDepartmentAndIdrCompanyOrderBySn(Long year,Department department,IdrCompany idrCompany){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeptKpiItem> query = builder.createQuery(DeptKpiItem.class);
		Root<DeptKpiItem> from = query.from(DeptKpiItem.class);
		Predicate criteria=builder.and(
				builder.equal(from.get("deptKpi").get("year"), year),
				builder.equal(from.get("deptKpi").get("department"), department),
				builder.equal(from.get("idrCompany"), idrCompany)
		);
		query.where(criteria);
		query.orderBy(builder.asc(from.get("sn")));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void remove(DeptKpiItem deptKpiItem) {
		deptKpiItem=entityManager.merge(deptKpiItem);
		entityManager.remove(deptKpiItem);
	}

}
