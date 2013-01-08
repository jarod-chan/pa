package cn.fyg.pa.domain.model.summary;


public interface PersonSummaryRepository {
	
	PersonSummary save(PersonSummary personSummary);
	
	PersonSummary findByYearAndPersonId(Long year,Long personId);
	
}
