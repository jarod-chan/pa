package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;


import cn.fyg.pa.domain.model.department.Department;

@Repository
public class DepartmentDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public Department findByName(String name) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Department> query=builder.createQuery(Department.class);
		Root<Department> root=query.from(Department.class);
		query.select(root);
		query.where(builder.equal(root.get("name"), name));
		List<Department> ret=entityManager.createQuery(query).setMaxResults(1).getResultList();
		if(ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
	
	public List<Department> findByPerson(Long personId){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Department> query=builder.createQuery(Department.class);
		Root<Department> root=query.from(Department.class);
		query.select(root);
		query.where(builder.equal(root.get("gmange_id"), personId));
		return entityManager.createQuery(query).getResultList();
	}

	//XXX 重构到repository 待删除
	public List<Department> findAllDepartmentsOrderById() {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Department> query=builder.createQuery(Department.class);
		Root<Department> root=query.from(Department.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

}
