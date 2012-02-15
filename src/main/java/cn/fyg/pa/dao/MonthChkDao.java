package cn.fyg.pa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.model.MonthChk;

@Repository
public class MonthChkDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public MonthChk save(MonthChk monthChk) {
		if (monthChk.getId() == null) {
			entityManager.persist(monthChk);
			return monthChk;
		} else {
			return entityManager.merge(monthChk);
		}		
	}	

}
