package cn.fyg.pa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.model.IdrYear;


@Repository
public class IdrYearDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public IdrYear find(Long id) {
		return entityManager.find(IdrYear.class, id);
	}
	
	@Transactional
	public IdrYear save(IdrYear idrYear){
		return idrYear;
	}
	
	public IdrYear remove(Long id){
		return null;
	}

}
