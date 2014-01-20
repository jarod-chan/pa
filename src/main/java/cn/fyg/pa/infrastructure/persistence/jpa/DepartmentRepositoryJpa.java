package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.person.Person;

@Repository
public class DepartmentRepositoryJpa implements DepartmentRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Department find(Long id) {
		return entityManager.find(Department.class, id);
	}

	@Override
	public List<Department> findAllDepartmentsOrderById() {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Department> query=builder.createQuery(Department.class);
		Root<Department> root=query.from(Department.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public List<Department> findDepartmentsByGmanage(Person gmanage){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Department> query=builder.createQuery(Department.class);
		Root<Department> root=query.from(Department.class);
		query.select(root);
		query.where(builder.equal(root.get("gmange_id"), gmanage.getId()));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public Department findDepartmentByName(String name) {
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

}
