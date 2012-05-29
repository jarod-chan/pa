package cn.fyg.pa.domain.model.deptmonthplan;

import cn.fyg.pa.domain.model.deptmonthplan.state.ExecuteAction;
import cn.fyg.pa.domain.model.deptmonthplan.state.FinishedAction;
import cn.fyg.pa.domain.model.deptmonthplan.state.NewAction;
import cn.fyg.pa.domain.model.deptmonthplan.state.SavedAction;
import cn.fyg.pa.domain.model.deptmonthplan.state.SubmittedAction;
import cn.fyg.pa.domain.shared.CommonEnum;
import cn.fyg.pa.domain.shared.state.StateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;
import cn.fyg.pa.domain.shared.state.StateEnum;

public enum IdrMonthPlanEnum implements CommonEnum,StateEnum<IdrMonthPlanBill>{
	NEW("新建",new NewAction()),
	SAVED("暂存",new SavedAction()),
	SUBMITTED("已提交",new SubmittedAction()),
	EXECUTE("执行中",new ExecuteAction()),
	FINISHED("已完成",new FinishedAction());
	
	protected String name;
	
	protected StateAction<IdrMonthPlanBill> stateAction;
	
	
	private IdrMonthPlanEnum(String name,StateAction<IdrMonthPlanBill> stateAction){
		this.name=name;
		this.stateAction=stateAction;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public StateAction<IdrMonthPlanBill> getStateAction() {
		return stateAction;
	}

	public void setStateAction(StateAction<IdrMonthPlanBill> stateAction) {
		this.stateAction = stateAction;
	}

	@Override
	public void next(IdrMonthPlanBill idrMonthPlanBill) throws StateChangeException {
		this.stateAction.next(idrMonthPlanBill);
	}

	@Override
	public void back(IdrMonthPlanBill idrMonthPlanBill) throws StateChangeException {
		this.stateAction.back(idrMonthPlanBill);
	}


}
