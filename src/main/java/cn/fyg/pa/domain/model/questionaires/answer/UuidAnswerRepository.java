package cn.fyg.pa.domain.model.questionaires.answer;

import java.util.List;


public interface UuidAnswerRepository {
	
	UuidAnswer save(UuidAnswer uuidAnswer);
	
	UuidAnswer find(Long id);
	
	List<UuidAnswer> findByUuidAndQtidAndPartid(String uuid,Long qtid,Long partid);
	
}
