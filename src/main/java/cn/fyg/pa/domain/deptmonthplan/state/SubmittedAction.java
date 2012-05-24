package cn.fyg.pa.domain.deptmonthplan.state;

import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.deptmonthplan.state.abstractaction.MonthPlanStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public class SubmittedAction extends MonthPlanStateAction implements
		StateAction<IdrMonthPlanBill> {

	@Override
	protected boolean checkNext(IdrMonthPlanBill idrMonthPlanBill) throws StateChangeException {
		return true;
	}
	@Override
	protected void doNext(IdrMonthPlanBill idrMonthPlanBill) {
		idrMonthPlanBill.setState(IdrMonthPlanEnum.EXECUTE);
	}
	
	@Override
	protected boolean checkBack(IdrMonthPlanBill idrMonthPlanBill) throws StateChangeException{
		return true;
	}

	@Override
	protected void doBack(IdrMonthPlanBill idrMonthPlanBill) {
		idrMonthPlanBill.setState(IdrMonthPlanEnum.SAVED);
	}

}
