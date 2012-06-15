package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshot;

public interface SummarySnapshotService {

	SummarySnapshot save(SummarySnapshot summarySnapshot);
	
	boolean isExistSummarySnapshotByPeriod(Long year,Long month);

	List<SummarySnapshot> getSummarySnapshotByYear(Long year);
	
	void remove(Long summarySnapshotId);
	
}
