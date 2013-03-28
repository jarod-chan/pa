package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.atten.opinion.Opinion;
import cn.fyg.pa.domain.model.atten.opinion.OpinionRepository;

@Repository
public class OpinionRepositoryJpa implements OpinionRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Opinion save(Opinion opinion) {
		if(opinion.getId()==null){
			opinion.setDate(new Date());
			entityManager.persist(opinion);
			return opinion;
		}
		return entityManager.merge(opinion);
	}

	@Override
	public Opinion find(Long id) {
		return entityManager.find(Opinion.class, id);
	}

	@Override
	public List<Opinion> findByBusinessCodeAndBusinessIdOrderByIdAsc(
			String businessCode, Long businessId) {
		String jpql="select o from Opinion o where o.businessCode=:businessCode and o.businessId=:businessId order by o.id asc ";
		List<Opinion> resultList = entityManager.createQuery(jpql, Opinion.class)
			.setParameter("businessCode", businessCode)
			.setParameter("businessId", businessId)
			.getResultList();
		return resultList;
	}

}
