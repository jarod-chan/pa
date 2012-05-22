package cn.fyg.pa.infrastructure.perisistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.yeartypeweight.IdrTypeWeight;
import cn.fyg.pa.domain.yeartypeweight.IdrYearTypeWeight;

@Repository
public class IdrYearTypeWeightDao implements BaseDao<IdrYearTypeWeight>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public IdrYearTypeWeight find(Long id) {
		return entityManager.find(IdrYearTypeWeight.class, id);
	}

	@Override
	public IdrYearTypeWeight save(IdrYearTypeWeight idrYearTypeWeight) {
		for(IdrTypeWeight idrTypeWeight:idrYearTypeWeight.getIdrTypeWeight()){
			idrTypeWeight.setIdrYearTypeWeight(idrYearTypeWeight);
		}
		IdrYearTypeWeight oldIdrYearTypeWeight=entityManager.find(IdrYearTypeWeight.class, idrYearTypeWeight.getYear());
		if(oldIdrYearTypeWeight==null){
			return create(idrYearTypeWeight);
		}
		return	update(idrYearTypeWeight,oldIdrYearTypeWeight);
	}

	private IdrYearTypeWeight update(IdrYearTypeWeight idrYearTypeWeight,
			IdrYearTypeWeight oldIdrYearTypeWeight) {
		Set<Long> typeWeightIdSet=new HashSet<Long>();
		for(IdrTypeWeight idrTypeWeight:idrYearTypeWeight.getIdrTypeWeight()){
			typeWeightIdSet.add(idrTypeWeight.getId());
		}
		for(IdrTypeWeight idrTypeWeight:oldIdrYearTypeWeight.getIdrTypeWeight()){
			if(!typeWeightIdSet.contains(idrTypeWeight.getId())){
				entityManager.remove(idrTypeWeight);
			}
		}
		return entityManager.merge(idrYearTypeWeight);
	}

	private IdrYearTypeWeight create(IdrYearTypeWeight idrYearTypeWeight) {
		entityManager.persist(idrYearTypeWeight);
		return idrYearTypeWeight;
	}

	@Override
	public void remove(IdrYearTypeWeight t) {
		// TODO Auto-generated method stub
		
	}

}
