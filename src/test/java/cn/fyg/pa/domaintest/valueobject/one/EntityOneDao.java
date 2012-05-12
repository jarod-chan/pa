package cn.fyg.pa.domaintest.valueobject.one;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class EntityOneDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void save(EntityOne en){
		entityManager.persist(en);
	}
	
	public EntityOne find(Long id){
		return entityManager.find(EntityOne.class, id);
	}

}
