package cn.fyg.pa.domain.model.deptmonthplan;

import java.util.List;

import cn.fyg.pa.domain.model.department.Department;


public interface IdrMonthPlanBillRepository {
	

	IdrMonthPlanBill find(Long id);
	
	IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill);
	
	IdrMonthPlanBill findLastIdrMonthPlanBill(Department department);
	
	IdrMonthPlanBill findIdrMonthPlanBillByPeriodAndDepartment(Long year, Long month,Department department);
	
	List<IdrMonthPlanBill> findIdrMonthPlanBillByPeriod(Long year, Long month);
	
	List<IdrMonthPlanBill> findIdrMonthPlanBillByDepartmentAndState(Department department,IdrMonthPlanEnum... state);
	
	List<IdrMonthPlanBill> findIdrMonthPlanBillByDepartmentAndState(List<Department> departments,IdrMonthPlanEnum... state);
	
	List<IdrMonthPlanBill> findIdrMonthPlanBillByPeriodAndDepartmentAndState(Long year,Long month,List<Department> departments,IdrMonthPlanEnum... state);

	List<IdrMonthPlanBill> findIdrMonthPlanBillByPeriodAndState(Long year,Long month,IdrMonthPlanEnum... state);
	
	List<IdrMonthPlanBill> findIdrMonthPlanBillByPeriodAndDepartmentAndState(Long year,Long month,Department depatrment,IdrMonthPlanEnum... state);

	
	
}
