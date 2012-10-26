package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.questionaires.answer.UuidShort;
import cn.fyg.pa.domain.model.questionaires.answer.UuidShortRepository;

@Repository
public class UuidShortRepositoryJpa implements UuidShortRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public UuidShort save(UuidShort uuidShort) {
		if(uuidShort.getId()==null){
			entityManager.persist(uuidShort);
			return uuidShort;
		}
		return entityManager.merge(uuidShort);
	}

	@Override
	public UuidShort find(Long id) {
		return entityManager.find(UuidShort.class, id);
	}

	@Override
	public List<UuidShort> findByUuidAndQtidAndPartid(String uuid, Long qtid,
			Long partid) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UuidShort> query = builder.createQuery(UuidShort.class);
		Root<UuidShort> from = query.from(UuidShort.class);
		query.where(builder.equal(from.get("qtid"),qtid),
				builder.equal(from.get("partid"),partid),
				builder.equal(from.get("uuid"), uuid));
		query.orderBy(builder.asc(from.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

}
