package cn.fyg.pa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import cn.fyg.pa.model.IdrType;

@Repository
public class IdrTypeDao {
	
	@PersistenceContext
	private EntityManager entityManager;


	public IdrType find(Long id) {
		return entityManager.find(IdrType.class, id);
	}
	
	public List<IdrType> findAll() {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<IdrType> query=builder.createQuery(IdrType.class);
		Root<IdrType> root=query.from(IdrType.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("id")));
		return entityManager.createQuery(query).getResultList();
	}
	
	public IdrType save(IdrType idrType) {
		if(idrType.getId()==null){
			entityManager.persist(idrType);
			return idrType;
		}else
			return entityManager.merge(idrType);
	}

	public void remove(IdrType idrType) {
		entityManager.remove(idrType);
	}
	
	

}
