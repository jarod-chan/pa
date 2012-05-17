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
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItemRepository;

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
		DeptKpi deptKpi=deptKpiItem.getDeptKpi();
		deptKpi=getDeptKpiByYearAndDepartment(deptKpi.getYear(),deptKpi.getDepartment());
		if(deptKpiItem.getId()==null){
			entityManager.persist(deptKpiItem);
			return deptKpiItem;
		}
		return entityManager.merge(deptKpiItem);
	}

	private DeptKpi getDeptKpiByYearAndDepartment(Long year,
			Department department) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeptKpi> query = builder.createQuery(DeptKpi.class);
		Root<DeptKpi> from = query.from(DeptKpi.class);
		Predicate criteria=builder.and(
				builder.equal(from.get("year"), year),
				builder.equal(from.get("department"), department)
				);
		query.where(criteria);
		List<DeptKpi> resultList = entityManager.createQuery(query).getResultList();
		if(resultList.isEmpty()){
			DeptKpi deptKpi=new DeptKpi();
			deptKpi.setYear(year);
			deptKpi.setDepartment(department);
			return deptKpi;
		}
		return resultList.get(0);
	}

	@Override
	public List<DeptKpiItem> findByYearAndDepartmentOrderBySn(Long year,
			Department department) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeptKpiItem> query = builder.createQuery(DeptKpiItem.class);
		Root<DeptKpiItem> from = query.from(DeptKpiItem.class);
		Predicate criteria=builder.and(
				builder.equal(from.get("deptKpi").get("year"), year),
				builder.equal(from.get("deptKpi").get("department"), department)
		);
		query.where(criteria);
		query.orderBy(builder.asc(from.get("sn")));
		return entityManager.createQuery(query).getResultList();
	}

}
