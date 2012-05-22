package cn.fyg.pa.domain.deptmonthplan;

import java.util.List;


public interface IdrMonthPlanBillRepository {
	
	List<IdrMonthPlanBill> findByPeriod(Long year, Long month);
	
}
