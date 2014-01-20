package cn.fyg.pa.domain.model.summarysnapshot;

import cn.fyg.pa.domain.model.summarysnapshot.state.ConfirmedAction;
import cn.fyg.pa.domain.model.summarysnapshot.state.ReceivedAction;
import cn.fyg.pa.domain.shared.CommonEnum;
import cn.fyg.pa.domain.shared.state.StateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;
import cn.fyg.pa.domain.shared.state.StateEnum;

public enum SnapshotEnum implements CommonEnum,StateEnum<SummarySnapshot> {
	RECEIVED("已接收",new ReceivedAction()),
	CONFIRMED("已确认",new ConfirmedAction());

	protected String name;
	private StateAction<SummarySnapshot> stateAction;
	
	private SnapshotEnum(String name, StateAction<SummarySnapshot> stateAction) {
		this.name = name;
		this.stateAction = stateAction;
	}

	@Override
	public void next(SummarySnapshot summarySnapshot) throws StateChangeException {
		this.stateAction.next(summarySnapshot);
	}

	@Override
	public void back(SummarySnapshot summarySnapshot) throws StateChangeException {
		this.stateAction.back(summarySnapshot);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.setName(name);
	}

}
