package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.questionaires.answer.UuidAnswer;
import cn.fyg.pa.domain.model.questionaires.answer.UuidAnswerRepository;

@Repository
public class UuidAnswerRepositoryJap implements UuidAnswerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UuidAnswer save(UuidAnswer uuidAnswer) {
		if(uuidAnswer.getId()==null){
			entityManager.persist(uuidAnswer);
			return uuidAnswer;
		}
		return entityManager.merge(uuidAnswer);
	}

	@Override
	public List<UuidAnswer> findByUuidAndQtidAndPartid(String uuid, Long qtid,
			Long partid) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UuidAnswer> query = builder.createQuery(UuidAnswer.class);
		Root<UuidAnswer> from = query.from(UuidAnswer.class);
		query.where(builder.equal(from.get("qtid"),qtid),
				builder.equal(from.get("partid"),partid),
				builder.equal(from.get("uuid"), uuid));
		query.orderBy(builder.asc(from.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public UuidAnswer find(Long id) {
		return entityManager.find(UuidAnswer.class, id);
	}

}
