package cn.fyg.pa.infrastructure.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.questionaires.key.Key;
import cn.fyg.pa.domain.model.questionaires.key.KeyRepository;

@Repository
public class KeyRepositoryJpa implements KeyRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Key find(String key) {
		return entityManager.find(Key.class, key);
	}

	@Override
	public Key save(Key key) {
		Key pKey = entityManager.find(Key.class, key);
		if(pKey==null){
			entityManager.persist(key);
			return key;
		}
		return entityManager.merge(key);
	}

}
