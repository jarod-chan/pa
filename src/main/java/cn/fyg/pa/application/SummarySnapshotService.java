package cn.fyg.pa.application;

import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshot;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public interface SummarySnapshotService {

	SummarySnapshot save(SummarySnapshot summarySnapshot);
	
	void remove(Long summarySnapshotId);
	
	SummarySnapshot next(Long summarySnapshotId) throws StateChangeException;
	
	SummarySnapshot back(Long summarySnapshotId) throws StateChangeException;
}
