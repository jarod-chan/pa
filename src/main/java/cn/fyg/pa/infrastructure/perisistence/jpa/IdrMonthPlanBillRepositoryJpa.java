package cn.fyg.pa.infrastructure.perisistence.jpa;

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

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.model.deptmonthplan.IdrTask;

@Repository
public class IdrMonthPlanBillRepositoryJpa implements IdrMonthPlanBillRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public IdrMonthPlanBill find(Long id) {
		return entityManager.find(IdrMonthPlanBill.class, id);
	}
	
	@Override
	public IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill) {
		for(IdrTask idrTask:idrMonthPlanBill.getIdrTasks()){
			idrTask.setIdrTaskBill(idrMonthPlanBill);
			if(idrTask.getContext()!=null){
				idrTask.setContext(idrTask.getContext().trim());
			}
			if(idrTask.getSummary()!=null){
				idrTask.setSummary(idrTask.getSummary().trim());
			}
		}
		if(idrMonthPlanBill.getId()==null){
			return create(idrMonthPlanBill);
		}
		return update(idrMonthPlanBill);
	}
	
	private IdrMonthPlanBill create(IdrMonthPlanBill idrMonthPlanBill) {
		entityManager.persist(idrMonthPlanBill);
		return idrMonthPlanBill;
	}

	private IdrMonthPlanBill update(IdrMonthPlanBill idrMonthPlanBill) {
		IdrMonthPlanBill oldIdrMonthPlanBill= entityManager.find(IdrMonthPlanBill.class,idrMonthPlanBill.getId());
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
	
	@Override
	public IdrMonthPlanBill findLastIdrMonthPlanBill(Department department) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<IdrMonthPlanBill> query=builder.createQuery(IdrMonthPlanBill.class);
		Root<IdrMonthPlanBill> root=query.from(IdrMonthPlanBill.class);
		query.select(root);
		query.where(builder.equal(root.get("department"), department));
		query.orderBy(builder.desc(root.get("year")),builder.desc(root.get("month")));
		List<IdrMonthPlanBill> retList=entityManager.createQuery(query).setMaxResults(1).getResultList();
		return retList.isEmpty()?null:retList.get(0);
	}
	
	@Override
	public List<IdrMonthPlanBill> findIdrMonthPlanBillByDepartmentAndState(Department department, IdrMonthPlanEnum... state) {
		return findByPeriodAndDepartmentAndState(null,null,new Department[]{department},state);
	}
	
	@Override
	public List<IdrMonthPlanBill> findIdrMonthPlanBillByDepartmentAndState(List<Department> departments, IdrMonthPlanEnum... state) {
		Department[] departmentsArray=departments.toArray(new Department[departments.size()]);
		return findByPeriodAndDepartmentAndState(null,null,departmentsArray,state);
	}

	
	@Override
	public List<IdrMonthPlanBill> findIdrMonthPlanBillByPeriod(Long year, Long month) {
		return findByPeriodAndDepartmentAndState(year, month, null);
	}

	@Override
	public List<IdrMonthPlanBill> findIdrMonthPlanBillByPeriodAndDepartmentAndState(Long year, Long month, Department depatrment,IdrMonthPlanEnum... state) {
		return findByPeriodAndDepartmentAndState(year, month, new Department[]{depatrment}, state);
	}

	@Override
	public List<IdrMonthPlanBill> findIdrMonthPlanBillByPeriodAndState(Long year, Long month, IdrMonthPlanEnum... state) {
		return findByPeriodAndDepartmentAndState(year, month,null,state);
	}
	
	private List<IdrMonthPlanBill> findByPeriodAndDepartmentAndState(Long year,Long month,Department[] department,IdrMonthPlanEnum... states){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<IdrMonthPlanBill> query=builder.createQuery(IdrMonthPlanBill.class);
		Root<IdrMonthPlanBill> root=query.from(IdrMonthPlanBill.class);
		query.select(root);
		List<Predicate> criteria=new ArrayList<Predicate>();
		if(year!=null){
			criteria.add(builder.equal(root.get("year"), year));
		}
		if(month!=null){
			criteria.add(builder.equal(root.get("month"), month));
		}
		if(department!=null&&department.length>0){
			criteria.add(root.get("department").in((Object[])department));
		}
		if(states!=null&&states.length>0){
			criteria.add(root.get("state").in((Object[])states));
		}
		
		if(criteria.size()==1){
			query.where(criteria.get(0));
		}else{
			query.where(builder.and(criteria.toArray(new Predicate[0])));
		}
		query.orderBy(builder.desc(root.get("year")),
				builder.desc(root.get("month")),
				builder.asc(root.get("department")));
		return entityManager.createQuery(query).getResultList();	
	}

}
