package cn.fyg.pa.application.questionaires;

import java.util.List;

import cn.fyg.pa.domain.model.questionaires.answer.UuidShort;

public interface ShortAnswerService {
	
	UuidShort create(String uuid,Long qtid,Long partid);
	
	void save(List<UuidShort> uuidShorts);
	
	List<UuidShort> findUuidShort(String uuid,Long qtid,Long partid);

	UuidShort find(Long id);

}
