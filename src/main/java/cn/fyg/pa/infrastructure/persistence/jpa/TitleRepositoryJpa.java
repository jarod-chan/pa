package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.summary.Title;
import cn.fyg.pa.domain.model.summary.TitleRepository;

@Repository
public class TitleRepositoryJpa implements TitleRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Title> findByYearOrderByNoASC(Long year) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Title> query = builder.createQuery(Title.class);
		Root<Title> from = query.from(Title.class);
		query.where(
			builder.equal(from.get("year"), year)
		);
		query.orderBy(builder.asc(from.get("no")));
		return entityManager.createQuery(query).getResultList();
	}

}
