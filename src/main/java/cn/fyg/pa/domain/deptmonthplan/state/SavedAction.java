package cn.fyg.pa.domain.deptmonthplan.state;

import org.apache.commons.lang.StringUtils;

import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.deptmonthplan.IdrTask;
import cn.fyg.pa.domain.deptmonthplan.state.abstractaction.MonthPlanStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public class SavedAction extends MonthPlanStateAction implements
		StateAction<IdrMonthPlanBill> {

	@Override
	protected boolean checkNext(IdrMonthPlanBill idrMonthPlanBill) throws StateChangeException {
		if(idrMonthPlanBill.getIdrTasks().isEmpty()){
			throw new StateChangeException("工作计划不能为空");
		}
		for(IdrTask idrTask:idrMonthPlanBill.getIdrTasks()){
			if(StringUtils.isBlank(idrTask.getContext())){
				throw new StateChangeException("工作计划内容不能为空");
			}
		}
		return true;
	}

	@Override
	protected void doNext(IdrMonthPlanBill idrMonthPlanBill) {
		idrMonthPlanBill.setState(IdrMonthPlanEnum.SUBMITTED);
	}

	@Override
	protected void doBack(IdrMonthPlanBill idrMonthPlanBill) {
	}


}
