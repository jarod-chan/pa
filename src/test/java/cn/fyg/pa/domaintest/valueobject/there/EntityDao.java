package cn.fyg.pa.domaintest.valueobject.there;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class EntityDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void save(EntityObject en){
		entityManager.persist(en);
	}
	
	public EntityObject find(Long id){
		return entityManager.find(EntityObject.class, id);
	}
	

}
