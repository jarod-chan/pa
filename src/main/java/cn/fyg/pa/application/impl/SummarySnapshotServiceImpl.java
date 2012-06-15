package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.SummarySnapshotService;
import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshot;
import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshotRepository;

@Service
public class SummarySnapshotServiceImpl implements SummarySnapshotService {
	
	@Resource
	SummarySnapshotRepository summarySnapshotRepository;

	@Override
	@Transactional
	public SummarySnapshot save(SummarySnapshot summarySnapshot) {
		return summarySnapshotRepository.save(summarySnapshot);
	}

	@Override
	public boolean isExistSummarySnapshotByPeriod(Long year, Long month) {
		List<SummarySnapshot> summarySnapshots = summarySnapshotRepository.findByPeriod(year, month);
		return summarySnapshots.isEmpty()?false:true;
	}
	
	@Override
	public List<SummarySnapshot> getSummarySnapshotByYear(Long year) {
		return  summarySnapshotRepository.findByPeriod(year, null);
	}

	@Override
	@Transactional
	public void remove(Long summarySnapshotId) {
		SummarySnapshot summarySnapshot = summarySnapshotRepository.find(summarySnapshotId);
		summarySnapshotRepository.remove(summarySnapshot);
	}
}
