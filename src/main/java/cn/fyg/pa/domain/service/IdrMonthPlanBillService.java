package cn.fyg.pa.domain.service;


import java.util.List;

import cn.fyg.pa.domain.model.Department;
import cn.fyg.pa.domain.model.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.domain.model.StateChangeException;
import cn.fyg.pa.domain.model.enums.IdrMonthPlanEnum;

public interface IdrMonthPlanBillService {
	
	IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill);
	
	IdrMonthPlanBill find(Long id);


	IdrMonthPlanBill getCurrentIdrMonthPlanBill(Department department);
	
	IdrMonthPlanBill next(Long id) throws StateChangeException;
	
	IdrMonthPlanBill back(Long id)throws StateChangeException;
	
	List<IdrMonthPlanBill> getIdrMonthPlanBillByDepartmentAndState(Department department,IdrMonthPlanEnum... state);
	
	List<IdrMonthPlanBill> getIdrMonthPlanBillByGmangeAndState(Person gmange,IdrMonthPlanEnum... state);

	List<IdrMonthPlanBill> getIdrMonthPlanBillByPeriodAndState(Long year,Long month,IdrMonthPlanEnum... state);
	
	List<IdrMonthPlanBill> getIdrMonthPlanBillByPeriodAndDepartmentAndState(Long year,Long month,Department depatrment,IdrMonthPlanEnum... state);

}
