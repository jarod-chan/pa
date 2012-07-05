package cn.fyg.pa.domaintest.valueobject.onetomany;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OneManyDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Five find(Long id){
		return entityManager.find(Five.class, id);
	}
	
	
	@Transactional
	public Five Save(Five one){
		entityManager.persist(one);
		return one;
	}
	
	
	@Transactional
	public Five update(Five one){
		return entityManager.merge(one);
	}

}
