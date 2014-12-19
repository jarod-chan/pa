package cn.fyg.pa.domain.model.summarysnapshot;

import java.util.List;

public interface SummarySnapshotRepository {
	
	SummarySnapshot find(Long id);

	SummarySnapshot save(SummarySnapshot summarySnapshot);
	
	List<SummarySnapshot> findByYear(Long year);
	
	SummarySnapshot findByPeriod(Long year,Long month);
	
	void remove(SummarySnapshot summarySnapshot);
}
