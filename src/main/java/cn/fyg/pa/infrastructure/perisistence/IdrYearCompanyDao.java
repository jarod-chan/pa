package cn.fyg.pa.infrastructure.perisistence;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.companykpi.IdrCompany;
import cn.fyg.pa.domain.companykpi.IdrYearCompany;
import cn.fyg.pa.domain.model.IdrTypeWeight;


@Repository
public class IdrYearCompanyDao implements BaseDao<IdrYearCompany> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public IdrYearCompany find(Long id) {
		IdrYearCompany idrYearCompany=entityManager.find(IdrYearCompany.class, id);
		return idrYearCompany;
	}
	
	private IdrYearCompany create(IdrYearCompany idrYearCompany) {
		entityManager.persist(idrYearCompany);
		return idrYearCompany;
	}
	
	private IdrYearCompany update(IdrYearCompany idrYearCompany,IdrYearCompany oldIdrYearCompany) {
		Set<Long> idrCompamyIdSet=new HashSet<Long>();
		for(IdrCompany idrCompany:idrYearCompany.getIdrCompany()){
			idrCompany.setIdrYearCompany(idrYearCompany);
			idrCompamyIdSet.add(idrCompany.getId());
		}
		for(IdrCompany idrCompany:oldIdrYearCompany.getIdrCompany()){
			if(!idrCompamyIdSet.contains(idrCompany.getId())){
				entityManager.remove(idrCompany);
			}
		}
		return entityManager.merge(idrYearCompany);
	}

	
	@Override
	public IdrYearCompany save(IdrYearCompany idrYearCompany){
		for(IdrCompany idrCompany:idrYearCompany.getIdrCompany()){
			idrCompany.setIdrYearCompany(idrYearCompany);
			IdrTypeWeight dbIdrTypeWeight=entityManager.find(IdrTypeWeight.class, idrCompany.getIdrTypeWeight().getId());
			idrCompany.setIdrTypeWeight(dbIdrTypeWeight);
			calculateRealWeight(idrCompany);
		}
		IdrYearCompany oldIdrYear=entityManager.find(IdrYearCompany.class, idrYearCompany.getYear());
		if(oldIdrYear==null){
			return create(idrYearCompany);
		}
		return update(idrYearCompany,oldIdrYear);
	}

	private void calculateRealWeight(IdrCompany idrCompany) {
		if(idrCompany.getWeight()==null) {
			idrCompany.setRealWeight(null);
			return;
		}
		if(idrCompany.getIdrTypeWeight()==null) {
			idrCompany.setRealWeight(null);
			return;
		}
		idrCompany.setRealWeight(idrCompany.getWeight().multiply(idrCompany.getIdrTypeWeight().getWeight()).setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	@Override
	public void remove(IdrYearCompany idrYear){
		entityManager.remove(idrYear);
	}

}
