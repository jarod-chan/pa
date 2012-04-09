package cn.fyg.pa.infrastructure.perisistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.MonthChkItem;

@Repository
public class MonthChkItemDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public MonthChkItem find(Long id){
		return entityManager.find(MonthChkItem.class, id);
	}
}
