package cn.fyg.pa.domaintest.mappedclass;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SonADao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public SonA find(Long id){
		return entityManager.find(SonA.class,id);
	}
	
	@Transactional
	public SonA save(SonA sonA){
		entityManager.persist(sonA);
		return sonA;
	}

}
