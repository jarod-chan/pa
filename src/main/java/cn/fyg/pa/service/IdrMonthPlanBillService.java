package cn.fyg.pa.service;

import cn.fyg.pa.model.Department;
import cn.fyg.pa.model.IdrMonthPlanBill;

public interface IdrMonthPlanBillService {
	
	IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill);
	
	IdrMonthPlanBill find(Long id);


	IdrMonthPlanBill getCurrentIdrMonthPlanBill(Department department);

}
