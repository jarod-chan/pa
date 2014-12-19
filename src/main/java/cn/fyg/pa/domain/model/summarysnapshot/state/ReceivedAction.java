package cn.fyg.pa.domain.model.summarysnapshot.state;

import cn.fyg.pa.domain.model.summarysnapshot.SnapshotEnum;
import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshot;
import cn.fyg.pa.domain.model.summarysnapshot.state.abstractaction.SnapshotStateAction;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public class ReceivedAction extends SnapshotStateAction {
	
	@Override
	protected boolean checkNext(SummarySnapshot summarySnapshot)
			throws StateChangeException {
		return true;
	}

	@Override
	protected void doNext(SummarySnapshot summarySnapshot) {
		summarySnapshot.setState(SnapshotEnum.CONFIRMED);
	}

	@Override
	protected void doBack(SummarySnapshot summarySnapshot) {

	}

}
