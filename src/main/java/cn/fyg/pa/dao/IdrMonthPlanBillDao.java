package cn.fyg.pa.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.model.Department;
import cn.fyg.pa.model.IdrMonthPlanBill;
import cn.fyg.pa.model.IdrTask;

@Repository
public class IdrMonthPlanBillDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public IdrMonthPlanBill find(Long id){
		return entityManager.find(IdrMonthPlanBill.class, id);
	}
	
	@Transactional
	public IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill) {
		for(IdrTask idrTask:idrMonthPlanBill.getIdrTasks()){
			idrTask.setIdrTaskBill(idrMonthPlanBill);
		}
		IdrMonthPlanBill oldIdrMonthPlanBill= entityManager.find(IdrMonthPlanBill.class,idrMonthPlanBill.getId());
		if(oldIdrMonthPlanBill!=null){
			return create(idrMonthPlanBill);
		}
		return update(idrMonthPlanBill,oldIdrMonthPlanBill);
	}
	
	private IdrMonthPlanBill create(IdrMonthPlanBill idrMonthPlanBill) {
		entityManager.persist(idrMonthPlanBill);
		return idrMonthPlanBill;
	}

	private IdrMonthPlanBill update(IdrMonthPlanBill idrMonthPlanBill,IdrMonthPlanBill oldIdrMonthPlanBill) {
		Set<Long> idrTaskIds=new HashSet<Long>();
		for (IdrTask idrTask : idrMonthPlanBill.getIdrTasks()) {
			idrTaskIds.add(idrTask.getId());
		}
		for(IdrTask idrTask :oldIdrMonthPlanBill.getIdrTasks()){
			if(!idrTaskIds.contains(idrTask.getId())){
				entityManager.remove(idrTask);
			}
		}
		return entityManager.merge(idrMonthPlanBill);
	}

	public IdrMonthPlanBill getMaxMonthIdrMonthPlanBill(Department department) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
