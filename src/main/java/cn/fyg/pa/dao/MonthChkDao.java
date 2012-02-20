package cn.fyg.pa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.MonthChkItem;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.enums.StateEnum;
import cn.fyg.pa.tool.CMonthChk;

@Repository
public class MonthChkDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public MonthChk find(Long id){
		return entityManager.find(MonthChk.class, id);
	}
	
	@Transactional
	public MonthChk save(MonthChk monthChk) {
		if (monthChk.getId() == null) {
			entityManager.persist(monthChk);
			return monthChk;
		} else {
			MonthChk oldMonthChk=this.find(monthChk.getId());
			oldMonthChk.subtractMonthChkItemsById(monthChk.getMonthChkItems());
			
			for(MonthChkItem item:oldMonthChk.getMonthChkItems()){
				entityManager.remove(item);
			}
			return entityManager.merge(monthChk);
		}		
	}	
	

	
	@Transactional
	public void remove(MonthChk monthChk) {
		entityManager.remove(entityManager.merge(monthChk));		
	}

	public MonthChk getMonthChk(Person person, Long year, Long month) {
		List<MonthChk> retList=entityManager.createQuery("select c from MonthChk c where c.person=:person and c.year=:year and c.month=:month", MonthChk.class)
				.setParameter("person", person)
				.setParameter("year", year)
				.setParameter("month", month)
		.getResultList();
		if(retList.isEmpty()){
			MonthChk monthChk=new MonthChk();
			monthChk.setPerson(person);
			monthChk.setYear(year);
			monthChk.setMonth(month);
			return monthChk;
		}
		return retList.get(0);
	}	
	
	public MonthChk getCurrMonthChk(Person person){
		List<MonthChk> retList=entityManager.createQuery("select c from MonthChk c where c.person=:person order by c.year desc,c.month desc", MonthChk.class)
				.setParameter("person", person)
				.setMaxResults(1)
		.getResultList();
		if(retList.isEmpty()){
			//TODO 该处代码应该放在别处
			MonthChk monthChk=new MonthChk();
			monthChk.setPerson(person);
			monthChk.setYear(CMonthChk.INIT_YEAR);
			monthChk.setMonth(CMonthChk.INIT_MONTH);
			monthChk.setState(StateEnum.SAVED);
			return monthChk;
		}
		return retList.get(0);
	}

}
