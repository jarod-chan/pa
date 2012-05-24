package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.IdrMonthPlanBillService;
import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBillFactory;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.shared.state.StateChangeException;
import cn.fyg.pa.infrastructure.perisistence.DepartmentDao;
import cn.fyg.pa.infrastructure.perisistence.IdrMonthPlanBillDao;

@Service
public class IdrMonthPlanBillServiceImp implements IdrMonthPlanBillService {
	
	//XXX   重构到此处，去掉dao重构到repository正在进行中。。。。。。。
	@Resource
	IdrMonthPlanBillDao idrMonthPlanBillDao;
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
	@Resource
	DepartmentDao departmentDao;
	
	@Override
	@Transactional
	public IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill) {
		return idrMonthPlanBillDao.save(idrMonthPlanBill);
	}



	@Override
	public IdrMonthPlanBill getCurrentIdrMonthPlanBill(Department department) {
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillRepository.findMaxMonthIdrMonthPlanBill(department);
		if(idrMonthPlanBill==null){
			return IdrMonthPlanBillFactory.createInitIdrMonthPlanBill(department);
		}
		if(isMaxMonthFinished(idrMonthPlanBill)){
			return IdrMonthPlanBillFactory.createNextIdrMonthPlanBill(department, idrMonthPlanBill.getYear(), idrMonthPlanBill.getMonth());
		}
		return idrMonthPlanBill;
	}

	private boolean isMaxMonthFinished(IdrMonthPlanBill idrMonthPlanBill) {
		return idrMonthPlanBill.getState()==IdrMonthPlanEnum.FINISHED;
	}

	

	@Override
	@Transactional
	public IdrMonthPlanBill next(Long id) throws StateChangeException {
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillRepository.find(id);
		idrMonthPlanBill.next();
		return idrMonthPlanBill;
	}

	@Override
	@Transactional
	public IdrMonthPlanBill back(Long id)
			throws StateChangeException {
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillRepository.find(id);
		idrMonthPlanBill.back();
		return idrMonthPlanBill;
	}

	

}
