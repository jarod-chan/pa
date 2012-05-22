package cn.fyg.pa.domain.monthchk.state;

import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.monthchk.state.abstractaction.MonthChkStateAction;
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
