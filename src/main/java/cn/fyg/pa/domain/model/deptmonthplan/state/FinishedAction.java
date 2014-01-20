package cn.fyg.pa.domain.model.deptmonthplan.state;

import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.state.abstractaction.MonthPlanStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;

public class FinishedAction extends MonthPlanStateAction implements
		StateAction<IdrMonthPlanBill> {

	@Override
	protected void doNext(IdrMonthPlanBill idrMonthPlanBill) {
		
	}

	@Override
	protected void doBack(IdrMonthPlanBill idrMonthPlanBill) {
		
	}

}
