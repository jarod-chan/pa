package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.yearchk.Fycheck;
import cn.fyg.pa.domain.model.yearchk.YearChkRepositroy;

@Repository
public class YearChkRepositroyJpa implements YearChkRepositroy{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Map<Long,Object[]> getPersonYearChkResult(Long year,Person person){
		String sql =" select personid,sum(win) as win,sum(draw) as draw,sum(lose) as lose from(" +
				"     select colid as personid,if(val=1,1,0) as win,if(val=0,1,0) as draw,if(val=-1,1,0) as lose  from fycheck where chkid=:personid and year=:year " +
				"     union all" +
				"     select rowid as personid,if(-val=1,1,0)as win,if(val=0,1,0) as draw,if(-val=-1,1,0) as lose from fycheck where chkid=:personid and year=:year " +
				") as temp group by personid ";
		Query query = entityManager.createNativeQuery(sql)
				.setParameter("personid", person.getId())
				.setParameter("year", year);
		@SuppressWarnings("unchecked")
		List<Object[]> tempList=query.getResultList();
		HashMap<Long,Object[]> map = new HashMap<Long,Object[]>();
		for(Object[] obj:tempList){
			map.put(((Integer)obj[0]).longValue(), obj);
		}
		return map;
	}


	@Override
	public List<Fycheck> getPersonYearChkAboutPerson(Long year,Person aboutPerson, Person chkPerson) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Fycheck> query = builder.createQuery(Fycheck.class);
		Root<Fycheck> root = query.from(Fycheck.class);
		Predicate chkPersonCriteria=builder.equal(root.get("chkId"), chkPerson.getId());
		Predicate yearCriteria=builder.equal(root.get("year"), year);
		Predicate aboutPersonCriteria=builder.equal(root.get("colId"), aboutPerson.getId());
		aboutPersonCriteria=builder.or(aboutPersonCriteria,builder.equal(root.get("rowId"), aboutPerson.getId()));
		query.where(chkPersonCriteria,yearCriteria,aboutPersonCriteria);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void saveFychecks(List<Fycheck> fychecks) {
		for (Fycheck fycheck : fychecks) {
			if (fycheck.getId() == null) {
				entityManager.persist(fycheck);
			} else {
				entityManager.merge(fycheck);
			}	
		}
	}

	@Override
	public List<Fycheck> getPersonYearChkByChkperson(Long year,Person person) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Fycheck> query = builder.createQuery(Fycheck.class);
		Root<Fycheck> root = query.from(Fycheck.class);
		Predicate yearCriteria=builder.equal(root.get("year"),year);
		Predicate chkPersonCriteria=builder.equal(root.get("chkId"), person.getId());
		query.where(yearCriteria,chkPersonCriteria);
		return entityManager.createQuery(query).getResultList();
	}
}
