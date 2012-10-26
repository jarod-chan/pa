package cn.fyg.pa.application.questionaires;

import java.util.List;

import cn.fyg.pa.domain.model.questionaires.answer.UuidAnswer;

public interface ChoiceAnswerService {
	
	UuidAnswer create(String uuid,Long qtid,Long partid);
	
	UuidAnswer save(UuidAnswer uuidAnswer);

	void save(List<UuidAnswer> uuidAnswers);
	
	UuidAnswer find(Long id);
	
	List<UuidAnswer> findAnswer(String uuid,Long qtid,Long partid);
}
