package cn.fyg.pa.application.questionaires;

import java.util.List;

import cn.fyg.pa.domain.model.questionaires.problem.ShortAnswer;

public interface ShortService {
	
	List<ShortAnswer> findPartShort(Long qtid,Long partid);
}
