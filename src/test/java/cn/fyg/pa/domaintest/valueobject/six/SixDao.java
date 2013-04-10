package cn.fyg.pa.domaintest.valueobject.six;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SixDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Six find(Long id){
		return entityManager.find(Six.class, id);
	}
	
	
	@Transactional
	public Six Save(Six one){
		entityManager.persist(one);
		return one;
	}
	
	
	@Transactional
	public Six update(Six one){
		return entityManager.merge(one);
	}

}
