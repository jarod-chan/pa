package cn.fyg.pa.infrastructure.perisistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.yearchk.ChkitemRepository;
import cn.fyg.pa.domain.model.yearchk.Fychkitem;

@Repository
public class ChkitemRepositoryJpa implements ChkitemRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public Fychkitem find(Long id) {
		return entityManager.find(Fychkitem.class, id);
	}

	@Override
	public List<Fychkitem> getItemsByYear(Long year) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Fychkitem> query = builder.createQuery(Fychkitem.class);
		Root<Fychkitem> from = query.from(Fychkitem.class);
		query.where(builder.equal(from.get("year"),year));
		query.orderBy(builder.asc(from.get("id")));
		return entityManager.createQuery(query).getResultList();
	}

}
