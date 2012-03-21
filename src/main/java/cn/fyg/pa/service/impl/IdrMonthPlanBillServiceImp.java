package cn.fyg.pa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.dao.IdrMonthPlanBillDao;
import cn.fyg.pa.model.Department;
import cn.fyg.pa.model.IdrMonthPlanBill;
import cn.fyg.pa.model.enums.IdrMonthPlanEnum;
import cn.fyg.pa.service.IdrMonthPlanBillService;
import cn.fyg.pa.tool.CMonthPlanBill;

@Service
public class IdrMonthPlanBillServiceImp implements IdrMonthPlanBillService {
	@Resource
	IdrMonthPlanBillDao idrMonthPlanBillDao;
	
	@Override
	public IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill) {
		return idrMonthPlanBillDao.save(idrMonthPlanBill);
	}

	@Override
	public IdrMonthPlanBill find(Long id) {
		return idrMonthPlanBillDao.find(id);
	}

	@Override
	public IdrMonthPlanBill getCurrentIdrMonthPlanBill(Department department) {
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillDao.getMaxMonthIdrMonthPlanBill(department);
		if(idrMonthPlanBill==null){
			return initIdrMonthPlanBill(department);
		}
		if(maxMonthIsFinished(idrMonthPlanBill)){
			return nextMonthIdrMonthPlanBill(idrMonthPlanBill);
		}
		return idrMonthPlanBill;
	}

	private IdrMonthPlanBill nextMonthIdrMonthPlanBill(
			IdrMonthPlanBill finishedMonthPlanBill) {
		IdrMonthPlanBill idrMonthPlanBill=new IdrMonthPlanBill();
		idrMonthPlanBill.setDepartment(finishedMonthPlanBill.getDepartment());
		Long currentYear = finishedMonthPlanBill.getYear();
		Long currentMonth = finishedMonthPlanBill.getMonth();
		if(currentMonth+1L>12){
			idrMonthPlanBill.setYear(currentYear+1L);
			idrMonthPlanBill.setMonth(1L);
		}else{
			idrMonthPlanBill.setYear(currentYear);
			idrMonthPlanBill.setMonth(currentMonth+1L);
		}
		return idrMonthPlanBill;
	}

	private boolean maxMonthIsFinished(IdrMonthPlanBill idrMonthPlanBill) {
		return idrMonthPlanBill.getState()==IdrMonthPlanEnum.FINISHED;
	}

	private IdrMonthPlanBill initIdrMonthPlanBill(Department department) {
		IdrMonthPlanBill idrMonthPlanBill=new IdrMonthPlanBill();
		idrMonthPlanBill.setDepartment(department);
		idrMonthPlanBill.setYear(CMonthPlanBill.INIT_YEAR);
		idrMonthPlanBill.setMonth(CMonthPlanBill.INIT_MONTH);
		return idrMonthPlanBill;
	}


}
