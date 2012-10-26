package cn.fyg.pa.domain.model.questionaires.problem;

import java.util.List;

public interface ShortAnswerRepository {
	
	List<ShortAnswer> findByQtidAndPartid(Long qtid, Long partid); 

}
