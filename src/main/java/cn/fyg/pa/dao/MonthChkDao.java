package cn.fyg.pa.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.MonthChkItem;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.enums.MonthChkEnum;

@Repository
public class MonthChkDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public MonthChk find(Long id){
		return entityManager.find(MonthChk.class, id);
	}
	
	public MonthChk save(MonthChk monthChk) {
		for (MonthChkItem monthChkItem : monthChk.getMonthChkItems()) {
			monthChkItem.setMonthChk(monthChk);
		}
		MonthChk oldMonthChk = null;
		if (monthChk.getId() != null) {
			oldMonthChk = entityManager.find(MonthChk.class, monthChk.getId());
		}
		if(oldMonthChk==null){
			return create(monthChk);
		}
		return update(monthChk,oldMonthChk);
	}
	
	private MonthChk create(MonthChk monthChk) {
		entityManager.persist(monthChk);
		return monthChk;
	}
	
	private MonthChk update(MonthChk monthChk, MonthChk oldMonthChk) {
		Set<Long> monthChkItemIds=new HashSet<Long>();
		for(MonthChkItem monthChkItem:monthChk.getMonthChkItems()){
			monthChkItemIds.add(monthChkItem.getId());
		}
		for(MonthChkItem monthChkItem:oldMonthChk.getMonthChkItems()){
			if(!monthChkItemIds.contains(monthChkItem.getId())){
				entityManager.remove(monthChkItem);
			}
		}
		return entityManager.merge(monthChk);
	}

	public void remove(MonthChk monthChk) {
		entityManager.remove(entityManager.merge(monthChk));		
	}
	
	public List<MonthChk> getAllMonthChkByPersonAndState(Long year,Long month,Person person,MonthChkEnum... states){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<MonthChk> query = builder.createQuery(MonthChk.class);
		Root<MonthChk> root = query.from(MonthChk.class);
		ArrayList<Predicate> criteria = new ArrayList<Predicate>();
		if(year!=null){
			criteria.add(builder.equal(root.get("year"), year));
		}
		if(month!=null){
			criteria.add(builder.equal(root.get("month"), month));
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
		query.orderBy(builder.desc(root.get("year"))
				,builder.desc(root.get("month")));
		return entityManager.createQuery(query).getResultList();
	}

	public List<MonthChk> findByPeriodAndState(Long year, Long month,String department,MonthChkEnum... states) {
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
		if(states!=null&&states.length>0){
			criteria.add(root.get("state").in((Object[])states));
		}
		if(department!=null){
			criteria.add(builder.equal(root.get("person").get("department"), department));
		}
		
		if(criteria.size()==1){
			query.where(criteria.get(0));
		}else{
			query.where(builder.and(criteria.toArray(new Predicate[0])));
		}
		query.orderBy(builder.asc(root.get("person")));
		return entityManager.createQuery(query).getResultList();
	}
	
	public MonthChk findMaxMonthMonthChk(Person person){
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

}
