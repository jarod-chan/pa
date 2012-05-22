package cn.fyg.pa.domain.monthchk.state.abstractaction;

import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.shared.state.AbstractStateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public abstract class MonthChkStateAction extends AbstractStateAction<MonthChk> {

	@Override
	protected boolean checkNext(MonthChk monthChk) throws StateChangeException {
		throw new StateChangeException(String.format("状态【%s】无法支持该操作！ ", monthChk.getState().getName()));
	}
	
	@Override
	protected abstract void doNext(MonthChk monthChk);
	
	@Override
	protected boolean checkBack(MonthChk monthChk) throws StateChangeException {
		throw new StateChangeException(String.format("状态【%s】无法支持该操作！ ", monthChk.getState().getName()));
	}

	@Override
	protected abstract void doBack(MonthChk monthChk);
}
