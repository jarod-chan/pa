package cn.fyg.pa.application.questionaires;

import java.util.List;

import cn.fyg.pa.domain.model.questionaires.ques.Ques;

public interface QuesService {
	
	Ques find(Long id);
	
	List<Ques> findAll();
	
	void finish(Long id);

}
