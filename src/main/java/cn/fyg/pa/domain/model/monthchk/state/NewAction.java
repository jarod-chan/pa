package cn.fyg.pa.domain.model.monthchk.state;

import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.state.abstractaction.MonthChkStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;

public class NewAction extends MonthChkStateAction implements StateAction<MonthChk> {

	@Override
	protected void doNext(MonthChk t) {
		
	}

	@Override
	protected void doBack(MonthChk t) {
		
	}


}
