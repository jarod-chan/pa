package cn.fyg.pa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.model.MonthChkItem;

@Repository
public class MonthChkItemDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public MonthChkItem find(Long id){
		return entityManager.find(MonthChkItem.class, id);
	}
}
