package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.usership.Usership;
import cn.fyg.pa.domain.model.usership.UsershipRepository;

@Repository
public class UsershipRepositoryJpa implements UsershipRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Usership find(String tree, String userKey) {
		String jpql="select u from Usership u where u.tree=:tree and u.key=:key ";
		List<Usership> resultList = entityManager.createQuery(jpql, Usership.class)
			.setParameter("tree", tree)
			.setParameter("key", userKey)
			.getResultList();
		return resultList.isEmpty()?null:resultList.get(0);
	}

	@Override
	public Usership save(Usership usership) {
		entityManager.persist(usership);
		return usership;
	}

}
