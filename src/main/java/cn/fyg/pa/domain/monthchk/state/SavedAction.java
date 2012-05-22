package cn.fyg.pa.domain.monthchk.state;

import org.apache.commons.lang.StringUtils;

import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.monthchk.MonthChkItem;
import cn.fyg.pa.domain.monthchk.state.abstractaction.MonthChkStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public class SavedAction extends MonthChkStateAction implements StateAction<MonthChk> {

	@Override
	protected boolean checkNext(MonthChk monthChk) throws StateChangeException {
		if(monthChk.getMonthChkItems().isEmpty()){
			throw new StateChangeException("月度工作任务不能为空");
		}
		for(MonthChkItem monthChkItem:monthChk.getMonthChkItems()){
			if(StringUtils.isBlank(monthChkItem.getTask())){
				throw new StateChangeException("工作内容不能为空");
			}
			if(monthChkItem.getWorkhour()==null){
				throw new StateChangeException("用时不能为空");
			}
		}
		return true;
	}

	@Override
	protected void doNext(MonthChk monthChk) {
		monthChk.setState(MonthChkEnum.SUBMITTED);
	}

	@Override
	protected void doBack(MonthChk t) {
	}
	
}
