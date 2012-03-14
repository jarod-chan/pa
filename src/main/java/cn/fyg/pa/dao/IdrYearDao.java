package cn.fyg.pa.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.model.IdrTypeWeight;
import cn.fyg.pa.model.IdrYear;


@Repository
public class IdrYearDao implements BaseDao<IdrYear> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public IdrYear find(Long id) {
		return entityManager.find(IdrYear.class, id);
	}
	
	private IdrYear create(IdrYear idrYear) {
		entityManager.persist(idrYear);
		return idrYear;
	}
	
	private IdrYear update(IdrYear idrYear,IdrYear oldIdrYear) {
		Set<Long> typeWeightIdSet=new HashSet<Long>();
		for(IdrTypeWeight idrTypeWeight:idrYear.getIdrTypeWeight()){
			typeWeightIdSet.add(idrTypeWeight.getId());
		}
		for(IdrTypeWeight idrTypeWeight:oldIdrYear.getIdrTypeWeight()){
			if(!typeWeightIdSet.contains(idrTypeWeight.getId())){
				entityManager.remove(idrTypeWeight);
			}
		}
		return entityManager.merge(idrYear);
	}

	
	@Override
	public IdrYear save(IdrYear idrYear){
		for(IdrTypeWeight idrTypeWeight:idrYear.getIdrTypeWeight()){
			idrTypeWeight.setIdrYear(idrYear);
		}
		IdrYear oldIdrYear=entityManager.find(IdrYear.class, idrYear.getYear());
		if(oldIdrYear==null){
			return create(idrYear);
		}else{
			return update(idrYear,oldIdrYear);
		}
	}


	
	@Override
	public void remove(IdrYear idrYear){
		entityManager.remove(idrYear);
	}

}
