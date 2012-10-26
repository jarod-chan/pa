package cn.fyg.pa.domain.model.questionaires.problem;

import java.util.List;

public interface ChoiceRepository {
	
	List<Choice> findByQtidAndPartid(Long qtid, Long partid); 

}
