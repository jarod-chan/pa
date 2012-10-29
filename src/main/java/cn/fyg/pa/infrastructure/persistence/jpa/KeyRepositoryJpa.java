package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		Key pKey = entityManager.find(Key.class, key.getUuid());
		if(pKey==null){
			entityManager.persist(key);
			return key;
		}
		return entityManager.merge(key);
	}

	@Override
	public List<Object[]> getKeyStateCount(Long qtid) {
		Query query = entityManager.createQuery("select K.state, count(K.qtid) from Key K where K.qtid=:qtid group by K.state");
		query.setParameter("qtid", new Long(1));
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

}
