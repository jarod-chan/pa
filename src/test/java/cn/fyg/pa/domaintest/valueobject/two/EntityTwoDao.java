package cn.fyg.pa.domaintest.valueobject.two;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class EntityTwoDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void save(EntityTwo en){
		entityManager.persist(en);
	}
	
	public EntityTwo find(Long id){
		return entityManager.find(EntityTwo.class, id);
	}

}
