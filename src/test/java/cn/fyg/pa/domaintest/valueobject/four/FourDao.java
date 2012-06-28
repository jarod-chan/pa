package cn.fyg.pa.domaintest.valueobject.four;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FourDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void save(Four four){
		entityManager.persist(four);
	}
	
	@Transactional
	public void update(Four four){
		entityManager.merge(four);
	}
	
	public Four find(Long id){
		return entityManager.find(Four.class, id);
	}

}
