package cn.fyg.pa.infrastructure.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.questionaires.ques.Ques;
import cn.fyg.pa.domain.model.questionaires.ques.QuesRepository;

@Repository
public class QuesRepositoryJpa implements QuesRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Ques find(Long id) {
		return entityManager.find(Ques.class, id);
	}

}
