package cn.fyg.pa.application;

import cn.fyg.pa.domain.model.summary.Content;
import cn.fyg.pa.domain.model.summary.PersonSummary;

public interface PersonSummaryService {
	
	PersonSummary findAndCreate(Long year,Long personId);
	
	PersonSummary find(Long year,Long personId);
	
	PersonSummary save(PersonSummary personSummary);
	
	Content save(Content content);

}
