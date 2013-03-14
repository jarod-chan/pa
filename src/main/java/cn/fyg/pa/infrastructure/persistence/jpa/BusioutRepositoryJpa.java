package cn.fyg.pa.infrastructure.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.busiout.Busiout;
import cn.fyg.pa.domain.model.busiout.BusioutRepository;

@Repository
public class BusioutRepositoryJpa implements BusioutRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Busiout save(Busiout busiout) {
		if(busiout.getId()==null){
			entityManager.persist(busiout);
			return busiout;
		}
		return entityManager.merge(busiout);
	}

}
