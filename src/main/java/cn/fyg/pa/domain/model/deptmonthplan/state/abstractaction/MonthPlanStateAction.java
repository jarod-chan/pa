package cn.fyg.pa.domain.model.deptmonthplan.state.abstractaction;

import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.shared.state.AbstractStateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public abstract class MonthPlanStateAction extends AbstractStateAction<IdrMonthPlanBill> {

	@Override
	protected boolean checkNext(IdrMonthPlanBill idrMonthPlanBill) throws StateChangeException {
		throw new StateChangeException(String.format("状态【%s】无法支持该操作！ ", idrMonthPlanBill.getState().getName()));
	}

	@Override
	protected abstract void doNext(IdrMonthPlanBill idrMonthPlanBill);

	@Override
	protected boolean checkBack(IdrMonthPlanBill idrMonthPlanBill) throws StateChangeException {
		throw new StateChangeException(String.format("状态【%s】无法支持该操作！ ", idrMonthPlanBill.getState().getName()));
	}

	@Override
	protected abstract void doBack(IdrMonthPlanBill idrMonthPlanBill);

}
