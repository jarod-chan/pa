package cn.fyg.pa.domain.model.monthchk.state;

import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.state.abstractaction.MonthChkStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;

public class FinishedAction extends MonthChkStateAction implements
		StateAction<MonthChk> {

	@Override
	protected void doNext(MonthChk monthChk) {
		
	}

	@Override
	protected void doBack(MonthChk monthChk) {
		
	}

}
