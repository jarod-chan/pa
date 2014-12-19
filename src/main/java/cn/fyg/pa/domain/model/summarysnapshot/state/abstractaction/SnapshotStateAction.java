package cn.fyg.pa.domain.model.summarysnapshot.state.abstractaction;

import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshot;
import cn.fyg.pa.domain.shared.state.AbstractStateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public abstract class SnapshotStateAction extends AbstractStateAction<SummarySnapshot> {

	@Override
	protected boolean checkNext(SummarySnapshot summarySnapshot) throws StateChangeException {
		throw new StateChangeException(String.format("状态【%s】无法支持该操作！ ", summarySnapshot.getState().getName()));
	}

	@Override
	protected abstract void doNext(SummarySnapshot summarySnapshot);

	@Override
	protected boolean checkBack(SummarySnapshot summarySnapshot) throws StateChangeException {
		throw new StateChangeException(String.format("状态【%s】无法支持该操作！ ", summarySnapshot.getState().getName()));
	}

	@Override
	protected abstract void doBack(SummarySnapshot summarySnapshot);
}
