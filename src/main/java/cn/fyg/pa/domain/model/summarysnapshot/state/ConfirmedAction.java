package cn.fyg.pa.domain.model.summarysnapshot.state;

import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshot;
import cn.fyg.pa.domain.model.summarysnapshot.state.abstractaction.SnapshotStateAction;

public class ConfirmedAction extends SnapshotStateAction {
	

	@Override
	protected void doNext(SummarySnapshot summarySnapshot) {
		
	}

	@Override
	protected void doBack(SummarySnapshot summarySnapshot) {

	}

}
