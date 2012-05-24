package cn.fyg.pa.domain.deptmonthplan.state;

import org.apache.commons.lang.StringUtils;

import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.deptmonthplan.IdrTask;
import cn.fyg.pa.domain.deptmonthplan.state.abstractaction.MonthPlanStateAction;
import cn.fyg.pa.domain.shared.state.StateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public class ExecuteAction extends MonthPlanStateAction implements
		StateAction<IdrMonthPlanBill> {
	
	@Override
	protected boolean checkNext(IdrMonthPlanBill idrMonthPlanBill) throws StateChangeException {
		for(IdrTask idrTask:idrMonthPlanBill.getIdrTasks()){
			if(StringUtils.isBlank(idrTask.getSummary())){
				throw new StateChangeException("工作总结不能为空");
			}
		}
		return true;
	}
	
	@Override
	protected void doNext(IdrMonthPlanBill idrMonthPlanBill) {
		idrMonthPlanBill.setState(IdrMonthPlanEnum.FINISHED);
	}

	@Override
	protected void doBack(IdrMonthPlanBill idrMonthPlanBill) {
	}



}
