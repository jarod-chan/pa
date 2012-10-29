package cn.fyg.pa.domain.model.questionaires.ques;

import java.util.List;

public interface QuesRepository {
	
	Ques find(Long id);

	List<Ques> findAll();
	
}
