package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.IdrMonthPlanBillService;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillFactory;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.shared.state.StateChangeException;

@Service
public class IdrMonthPlanBillServiceImp implements IdrMonthPlanBillService {
	
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
	
	@Override
	@Transactional
	public IdrMonthPlanBill save(IdrMonthPlanBill idrMonthPlanBill) {
		return idrMonthPlanBillRepository.save(idrMonthPlanBill);
	}

	@Override
	public IdrMonthPlanBill getLastIdrMonthPlanBill(Department department) {
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillRepository.findLastIdrMonthPlanBill(department);
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
