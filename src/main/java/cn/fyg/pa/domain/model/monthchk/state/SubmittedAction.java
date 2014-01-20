package cn.fyg.pa.domain.model.monthchk.state;

import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChkItem;
import cn.fyg.pa.domain.model.monthchk.state.abstractaction.MonthChkStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public class SubmittedAction extends MonthChkStateAction implements
		StateAction<MonthChk> {

	@Override
	protected boolean checkNext(MonthChk monthChk) throws StateChangeException {
		return true;
	}

	@Override
	protected void doNext(MonthChk monthChk) {
		monthChk.setState(MonthChkEnum.FINISHED);
	}
	
	@Override
	protected boolean checkBack(MonthChk monthChk) throws StateChangeException {
		return true;
	}

	@Override
	protected void doBack(MonthChk monthChk) {
		for(MonthChkItem monthChkItem: monthChk.getMonthChkItems()){
			monthChkItem.setPoint(null);
		}
		monthChk.setState(MonthChkEnum.SAVED);
	}

}
