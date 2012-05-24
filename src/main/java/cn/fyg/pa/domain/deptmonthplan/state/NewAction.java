package cn.fyg.pa.domain.deptmonthplan.state;

import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.deptmonthplan.state.abstractaction.MonthPlanStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;

public class NewAction extends MonthPlanStateAction implements
		StateAction<IdrMonthPlanBill> {

	@Override
	protected void doNext(IdrMonthPlanBill idrMonthPlanBill) {
	}

	@Override
	protected void doBack(IdrMonthPlanBill idrMonthPlanBill) {
	}


}
