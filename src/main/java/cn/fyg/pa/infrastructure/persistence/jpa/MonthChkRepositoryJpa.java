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

import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChkItem;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.Person;

@Repository
public class MonthChkRepositoryJpa implements MonthChkRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public MonthChk find(Long id) {
		return entityManager.find(MonthChk.class, id);
	}
	
	@Override
	public MonthChk save(MonthChk monthChk) {
		for (MonthChkItem monthChkItem : monthChk.getMonthChkItems()) {
			monthChkItem.setMonthChk(monthChk);
		}
		if(monthChk.getId()==null){
			entityManager.persist(monthChk);
			return monthChk;
		}
		return entityManager.merge(monthChk);
	}
	
	@Override
	public MonthChk findLastMonthMonthChk(Person person){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<MonthChk> query=builder.createQuery(MonthChk.class);
		Root<MonthChk> root=query.from(MonthChk.class);
		query.select(root);
		List<Predicate> criteria=new ArrayList<Predicate>();
		if(person!=null){
			criteria.add(builder.equal(root.get("person"), person));
		}
		if(criteria.size()==1){
			query.where(criteria.get(0));
		}else{
			query.where(builder.and(criteria.toArray(new Predicate[0])));
		}
		query.orderBy(builder.desc(root.get("year"))
				,builder.desc(root.get("month")));
		List<MonthChk> resultList = entityManager.createQuery(query).setMaxResults(1).getResultList();
		if(resultList.isEmpty()){
			return null;
		}
		return resultList.get(0);
	}
	

	@Override
	public List<MonthChk> findMonthChkByPeriod(Long year,Long month){
		return findByPeriodAndDepartmentAndPersonAndState(year, month, null, null);
	}


	@Override
	public List<MonthChk> getMonthChkByPeriodAndDepartmentAndState(Long year, Long month,String department,MonthChkEnum... states) {
		return findByPeriodAndDepartmentAndPersonAndState(year,month,department,null,states);
	}

	@Override
	public List<MonthChk> getMonthChkByDepartmentAndState(String department,MonthChkEnum... states) {
		return findByPeriodAndDepartmentAndPersonAndState(null,null,department,null,states);
	}

	@Override
	public List<MonthChk> getMonthChkByPersonAndState(Long year, Person person,MonthChkEnum... states) {
		return findByPeriodAndDepartmentAndPersonAndState(year,null,null,person, states);
	}
	
	private List<MonthChk> findByPeriodAndDepartmentAndPersonAndState(Long year, Long month,String department,Person person,MonthChkEnum... states){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<MonthChk> query=builder.createQuery(MonthChk.class);
		Root<MonthChk> root=query.from(MonthChk.class);
		query.select(root);
		List<Predicate> criteria=new ArrayList<Predicate>();
		if(year!=null){
			criteria.add(builder.equal(root.get("year"), year));
		}
		if(month!=null){
			criteria.add(builder.equal(root.get("month"), month));
		}
		if(department!=null){
			criteria.add(builder.equal(root.get("person").get("department"), department));
		}
		if(person!=null){
			criteria.add(builder.equal(root.get("person"), person));
		}
		if(states!=null&&states.length>0){
			criteria.add(root.get("state").in((Object[])states));
		}
		
		if(criteria.size()==1){
			query.where(criteria.get(0));
		}else{
			query.where(builder.and(criteria.toArray(new Predicate[0])));
		}
		query.orderBy(
				builder.desc(root.get("year")), 
				builder.desc(root.get("month")),
				builder.asc(root.get("person"))
			);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public MonthChk findMonthChkByPeriodAndPerson(Long year, Long month,
			Person person) {
		 List<MonthChk> list = findByPeriodAndDepartmentAndPersonAndState(year, month, null, person);
		 if(list.isEmpty()){
			 return null;
		 }
		 return list.get(0);
	}

}
