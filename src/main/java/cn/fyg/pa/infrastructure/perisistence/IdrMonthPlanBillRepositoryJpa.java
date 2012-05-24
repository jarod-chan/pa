package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.person.Person;

@Repository
public class IdrMonthPlanBillRepositoryJpa implements IdrMonthPlanBillRepository {

	@Resource
	IdrMonthPlanBillDao idrMonthPlanBillDao;
	@Resource
	DepartmentDao departmentDao;
	
	@Override
	public List<IdrMonthPlanBill> findByPeriod(Long year, Long month) {
		return idrMonthPlanBillDao.findByPeriodAndDepartmentAndState(year, month, null);
	}
	
	@Override
	public IdrMonthPlanBill find(Long id) {
		return idrMonthPlanBillDao.find(id);
	}
	
	@Override
	public List<IdrMonthPlanBill> getIdrMonthPlanBillByDepartmentAndState(Department department, IdrMonthPlanEnum... state) {
		return idrMonthPlanBillDao.findByPeriodAndDepartmentAndState(null,null,new Department[]{department},state);
	}

	@Override
	public List<IdrMonthPlanBill> getIdrMonthPlanBillByGmangeAndState(Person gmange, IdrMonthPlanEnum... state) {
		List<Department> departmentsList=departmentDao.findByPerson(gmange.getId());
		Department[] departmentsArray=departmentsList.toArray(new Department[departmentsList.size()]);
		return idrMonthPlanBillDao.findByPeriodAndDepartmentAndState(null,null,departmentsArray,state);
	}

	@Override
	public List<IdrMonthPlanBill> getIdrMonthPlanBillByPeriodAndState(Long year, Long month, IdrMonthPlanEnum... state) {
		return idrMonthPlanBillDao.findByPeriodAndDepartmentAndState(year, month,null,state);
	}

	@Override
	public List<IdrMonthPlanBill> getIdrMonthPlanBillByPeriodAndDepartmentAndState(
			Long year, Long month, Department depatrment,
			IdrMonthPlanEnum... state) {
		return idrMonthPlanBillDao.findByPeriodAndDepartmentAndState(year, month, new Department[]{depatrment}, state);
	}

	@Override
	public IdrMonthPlanBill findMaxMonthIdrMonthPlanBill(Department department) {
		return idrMonthPlanBillDao.findMaxMonthIdrMonthPlanBill(department);
	}

}
