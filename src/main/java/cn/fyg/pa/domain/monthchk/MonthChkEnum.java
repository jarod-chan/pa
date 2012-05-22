package cn.fyg.pa.domain.monthchk;

import cn.fyg.pa.domain.monthchk.state.FinishedAction;
import cn.fyg.pa.domain.monthchk.state.NewAction;
import cn.fyg.pa.domain.monthchk.state.SavedAction;
import cn.fyg.pa.domain.monthchk.state.SubmittedAction;
import cn.fyg.pa.domain.shared.CommonEnum;
import cn.fyg.pa.domain.shared.state.StateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;
import cn.fyg.pa.domain.shared.state.StateEnum;


public enum MonthChkEnum implements CommonEnum,StateEnum<MonthChk> {
	NEW("新建",new NewAction()),
	SAVED("暂存",new SavedAction()),
	SUBMITTED("已提交",new SubmittedAction()),
	FINISHED("已完成",new FinishedAction());
	
	protected String name;
	private StateAction<MonthChk> stateAction;
	
	private MonthChkEnum(String name,StateAction<MonthChk> stateAction){
		this.name=name;
		this.stateAction=stateAction;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public void next(MonthChk monthChk) throws StateChangeException {
		this.stateAction.next(monthChk);
	}

	@Override
	public void back(MonthChk monthChk) throws StateChangeException {
		this.stateAction.back(monthChk);
	}
	

	
}
