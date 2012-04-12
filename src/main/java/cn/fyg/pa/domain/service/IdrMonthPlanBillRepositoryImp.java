package cn.fyg.pa.domain.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.model.IdrMonthPlanBill;
import cn.fyg.pa.infrastructure.perisistence.IdrMonthPlanBillDao;

@Repository
public class IdrMonthPlanBillRepositoryImp implements IdrMonthPlanBillRepository {

	@Resource
	IdrMonthPlanBillDao idrMonthPlanBillDao;
	
	@Override
	public List<IdrMonthPlanBill> findByPeriod(Long year, Long month) {
		return idrMonthPlanBillDao.findByPeriodAndDepartmentAndState(year, month, null);
	}

}
