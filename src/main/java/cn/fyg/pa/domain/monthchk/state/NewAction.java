package cn.fyg.pa.domain.monthchk.state;

import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.monthchk.state.abstractaction.MonthChkStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;

public class NewAction extends MonthChkStateAction implements StateAction<MonthChk> {

	@Override
	protected void doNext(MonthChk t) {
		
	}

	@Override
	protected void doBack(MonthChk t) {
		
	}


}
