package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshot;
import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshotRepository;

@Repository
public class SummarySnapshotRepositoryJpa implements SummarySnapshotRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public SummarySnapshot find(Long id) {
		return entityManager.find(SummarySnapshot.class, id);
	}

	@Override
	public SummarySnapshot save(SummarySnapshot summarySnapshot) {
		if(summarySnapshot.getId()==null){
			return create(summarySnapshot);
		}
		return update(summarySnapshot);
	}

	private SummarySnapshot create(SummarySnapshot summarySnapshot) {
		entityManager.persist(summarySnapshot);
		return summarySnapshot;
	}

	private SummarySnapshot update(SummarySnapshot summarySnapshot) {
		return entityManager.merge(summarySnapshot);
	}
	
	@Override
	public List<SummarySnapshot> findByYear(Long year) {
		return findByYearAndMonth(year,null);
	}
	
	@Override
	public SummarySnapshot findByPeriod(Long year, Long month) {
		List<SummarySnapshot> summarySnapshots = findByYearAndMonth(year,month);
		return summarySnapshots.isEmpty()?null:summarySnapshots.get(0);
	}

	private List<SummarySnapshot> findByYearAndMonth(Long year, Long month) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SummarySnapshot> query = builder.createQuery(SummarySnapshot.class);
		Root<SummarySnapshot> from = query.from(SummarySnapshot.class);
		List<Predicate> predicates=new ArrayList<Predicate>();
		if(year!=null){
			predicates.add(builder.equal(from.get("year"), year));
		}
		if(month!=null){
			predicates.add(builder.equal(from.get("month"), month));
		}
		
		if(!predicates.isEmpty()){
			if(predicates.size()==1){
				query.where(predicates.get(0));
			}else{
				query.where(builder.and(predicates.toArray(new Predicate[0])));
			}
		}
		query.orderBy(
				builder.desc(from.get("year")),
				builder.desc(from.get("month")),
				builder.desc(from.get("logDate"))
				);
		return entityManager.createQuery(query).getResultList();

	}

	@Override
	public void remove(SummarySnapshot summarySnapshot) {
		entityManager.remove(summarySnapshot);
	}

}
