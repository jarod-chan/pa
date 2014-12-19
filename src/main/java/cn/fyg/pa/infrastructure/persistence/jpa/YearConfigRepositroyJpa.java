package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.yearchk.EnableYearNotExist;
import cn.fyg.pa.domain.model.yearchk.YearConfig;
import cn.fyg.pa.domain.model.yearchk.YearConfigRepositroy;

@Repository
public class YearConfigRepositroyJpa implements YearConfigRepositroy {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public YearConfig getEnableYear() throws EnableYearNotExist {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<YearConfig> query=builder.createQuery(YearConfig.class);
		Root<YearConfig> root=query.from(YearConfig.class);
		Predicate criteria=builder.equal(root.get("enable"), Boolean.TRUE);
		query.where(criteria);
		List<YearConfig> enableYear=entityManager.createQuery(query).setMaxResults(1).getResultList();
		if(enableYear.isEmpty()){
			throw new EnableYearNotExist();
		}
		return enableYear.get(0);
	}

	@Override
	public YearConfig find(Long year) {
		return entityManager.find(YearConfig.class, year);
	}
	
	@Override
	public YearConfig save(YearConfig yearConfig){
		YearConfig oldYearConfig=entityManager.find(YearConfig.class, yearConfig.getYear());
		if(oldYearConfig==null){
			entityManager.persist(yearConfig);
			return yearConfig;
		}
		return entityManager.merge(yearConfig);
	}

	@Override
	public List<YearConfig> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<YearConfig> query=builder.createQuery(YearConfig.class);
		@SuppressWarnings("unused")
		Root<YearConfig> root=query.from(YearConfig.class);
		return entityManager.createQuery(query).getResultList();
	}

}
