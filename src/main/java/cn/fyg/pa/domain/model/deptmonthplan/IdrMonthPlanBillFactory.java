package cn.fyg.pa.domain.model.deptmonthplan;

import cn.fyg.pa.domain.model.department.Department;

public class IdrMonthPlanBillFactory {
	
	public static Long INIT_YEAR=2012L;
	public static Long INIT_MONTH=4L;
	
	
	public static IdrMonthPlanBill createInitIdrMonthPlanBill(Department department){
		return createIdrMonthPlanBill(department,INIT_YEAR,INIT_MONTH);
	}
	
	public static IdrMonthPlanBill createNextIdrMonthPlanBill(Department department,Long year,Long month){
		month=month+1L;
		if(month.intValue()>12){
			year=year+1L;
			month=1L;
		}
		return createIdrMonthPlanBill(department,year,month);
	}
	
	public static IdrMonthPlanBill createIdrMonthPlanBill(Department department,Long year,Long month){
		IdrMonthPlanBill idrMonthPlanBill=new IdrMonthPlanBill();
		idrMonthPlanBill.setDepartment(department);
		idrMonthPlanBill.setState(IdrMonthPlanEnum.NEW);
		idrMonthPlanBill.setYear(year);
		idrMonthPlanBill.setMonth(month);
		return idrMonthPlanBill;
	}

}
