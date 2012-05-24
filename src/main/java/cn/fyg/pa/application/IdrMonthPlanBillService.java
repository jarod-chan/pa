package cn.fyg.pa.application;


import java.util.List;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public interface IdrMonthPlanBillService {
	
	IdrMonthPlanBill getCurrentIdrMonthPlanBill(Department department);
	
	IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill);
	
	IdrMonthPlanBill next(Long id) throws StateChangeException;
	
	IdrMonthPlanBill back(Long id)throws StateChangeException;
	
}
