package cn.fyg.pa.service;


import java.util.List;

import cn.fyg.pa.model.Department;
import cn.fyg.pa.model.IdrMonthPlanBill;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.StateChangeException;
import cn.fyg.pa.model.enums.IdrMonthPlanEnum;

public interface IdrMonthPlanBillService {
	
	IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill);
	
	IdrMonthPlanBill find(Long id);


	IdrMonthPlanBill getCurrentIdrMonthPlanBill(Department department);
	
	IdrMonthPlanBill next(Long id) throws StateChangeException;


	List<IdrMonthPlanBill> getAllIdrMonthPlanBillState(IdrMonthPlanEnum... state);
	
	List<IdrMonthPlanBill> getIdrMonthPlanBillByDepartmentAndState(Department department,IdrMonthPlanEnum... state);
	
	List<IdrMonthPlanBill> getIdrMonthPlanBillByGmangeAndState(Person gmange,IdrMonthPlanEnum... state);

	IdrMonthPlanBill back(Long id)throws StateChangeException;

}
