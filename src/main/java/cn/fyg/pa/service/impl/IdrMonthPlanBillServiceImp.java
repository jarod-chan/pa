package cn.fyg.pa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.dao.DepartmentDao;
import cn.fyg.pa.dao.IdrMonthPlanBillDao;
import cn.fyg.pa.model.Department;
import cn.fyg.pa.model.IdrMonthPlanBill;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.StateChangeException;
import cn.fyg.pa.model.enums.IdrMonthPlanEnum;
import cn.fyg.pa.service.IdrMonthPlanBillService;
import cn.fyg.pa.tool.CMonthPlanBill;

@Service
public class IdrMonthPlanBillServiceImp implements IdrMonthPlanBillService {
	@Resource
	IdrMonthPlanBillDao idrMonthPlanBillDao;
	
	@Resource
	DepartmentDao departmentDao;
	
	@Override
	@Transactional
	public IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill) {
		return idrMonthPlanBillDao.save(idrMonthPlanBill);
	}

	@Override
	public IdrMonthPlanBill find(Long id) {
		return idrMonthPlanBillDao.find(id);
	}

	@Override
	public IdrMonthPlanBill getCurrentIdrMonthPlanBill(Department department) {
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillDao.getMaxMonthIdrMonthPlanBill(department);
		if(idrMonthPlanBill==null){
			return initIdrMonthPlanBill(department);
		}
		if(maxMonthIsFinished(idrMonthPlanBill)){
			return nextMonthIdrMonthPlanBill(idrMonthPlanBill);
		}
		return idrMonthPlanBill;
	}

	private IdrMonthPlanBill nextMonthIdrMonthPlanBill(
			IdrMonthPlanBill finishedMonthPlanBill) {
		IdrMonthPlanBill idrMonthPlanBill=new IdrMonthPlanBill();
		idrMonthPlanBill.setDepartment(finishedMonthPlanBill.getDepartment());
		idrMonthPlanBill.setState(IdrMonthPlanEnum.SAVED);
		Long currentYear = finishedMonthPlanBill.getYear();
		Long currentMonth = finishedMonthPlanBill.getMonth();
		if(currentMonth+1L>12){
			idrMonthPlanBill.setYear(currentYear+1L);
			idrMonthPlanBill.setMonth(1L);
		}else{
			idrMonthPlanBill.setYear(currentYear);
			idrMonthPlanBill.setMonth(currentMonth+1L);
		}
		return idrMonthPlanBill;
	}

	private boolean maxMonthIsFinished(IdrMonthPlanBill idrMonthPlanBill) {
		return idrMonthPlanBill.getState()==IdrMonthPlanEnum.FINISHED;
	}

	private IdrMonthPlanBill initIdrMonthPlanBill(Department department) {
		IdrMonthPlanBill idrMonthPlanBill=new IdrMonthPlanBill();
		idrMonthPlanBill.setDepartment(department);
		idrMonthPlanBill.setState(IdrMonthPlanEnum.SAVED);
		idrMonthPlanBill.setYear(CMonthPlanBill.INIT_YEAR);
		idrMonthPlanBill.setMonth(CMonthPlanBill.INIT_MONTH);
		return idrMonthPlanBill;
	}

	@Override
	@Transactional
	public IdrMonthPlanBill next(Long id) throws StateChangeException {
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillDao.find(id);
		idrMonthPlanBill.next();
		return idrMonthPlanBill;
	}

	@Override
	@Transactional
	public IdrMonthPlanBill back(Long id)
			throws StateChangeException {
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillDao.find(id);
		idrMonthPlanBill.back();
		return idrMonthPlanBill;
	}

	@Override
	public List<IdrMonthPlanBill> getAllIdrMonthPlanBillState(IdrMonthPlanEnum... state) {
		return idrMonthPlanBillDao.findBillByState(state);
	}

	@Override
	public List<IdrMonthPlanBill> getIdrMonthPlanBillByDepartmentAndState(Department department, IdrMonthPlanEnum... state) {
		return idrMonthPlanBillDao.findBillByDepartmentAndState(new Department[]{department},state);
	}

	@Override
	public List<IdrMonthPlanBill> getIdrMonthPlanBillByGmangeAndState(Person gmange, IdrMonthPlanEnum... state) {
		List<Department> departmentsList=departmentDao.findByPerson(gmange.getId());
		Department[] departmentsArray=departmentsList.toArray(new Department[departmentsList.size()]);
		return idrMonthPlanBillDao.findBillByDepartmentAndState(departmentsArray,state);
	}

	@Override
	public List<IdrMonthPlanBill> getIdrMonthPlanBillByPeriodAndState(Long year, Long month, IdrMonthPlanEnum... state) {
		return idrMonthPlanBillDao.findByPeriodAndState(year, month,state);
	}

}
