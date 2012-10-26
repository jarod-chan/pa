package cn.fyg.pa.application.questionaires;

import java.util.List;

import cn.fyg.pa.domain.model.questionaires.problem.Choice;

public interface ChoiceService {
	
	List<Choice> findPartChoice(Long qtid,Long partid);

}
