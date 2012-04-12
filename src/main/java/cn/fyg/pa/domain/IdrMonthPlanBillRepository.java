package cn.fyg.pa.domain;

import java.util.List;

import cn.fyg.pa.domain.model.IdrMonthPlanBill;

public interface IdrMonthPlanBillRepository {
	
	List<IdrMonthPlanBill> findByPeriod(Long year, Long month);
	
}
