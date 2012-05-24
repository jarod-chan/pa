package cn.fyg.pa.domain.deptmonthplan;

import java.util.List;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.person.Person;


public interface IdrMonthPlanBillRepository {
	
	List<IdrMonthPlanBill> findByPeriod(Long year, Long month);

	IdrMonthPlanBill find(Long id);
	
	IdrMonthPlanBill findMaxMonthIdrMonthPlanBill(Department department);
	
	List<IdrMonthPlanBill> getIdrMonthPlanBillByDepartmentAndState(Department department,IdrMonthPlanEnum... state);
	
	List<IdrMonthPlanBill> getIdrMonthPlanBillByGmangeAndState(Person gmange,IdrMonthPlanEnum... state);

	List<IdrMonthPlanBill> getIdrMonthPlanBillByPeriodAndState(Long year,Long month,IdrMonthPlanEnum... state);
	
	List<IdrMonthPlanBill> getIdrMonthPlanBillByPeriodAndDepartmentAndState(Long year,Long month,Department depatrment,IdrMonthPlanEnum... state);
	
}
