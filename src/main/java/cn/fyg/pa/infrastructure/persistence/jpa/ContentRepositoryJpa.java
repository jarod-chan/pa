package cn.fyg.pa.infrastructure.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.summary.Content;
import cn.fyg.pa.domain.model.summary.ContentRepository;

@Repository
public class ContentRepositoryJpa implements ContentRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Content save(Content content) {
		if(content.getId()==null){
			entityManager.persist(content);
			return content;
		}
		return entityManager.merge(content);
	}

}
