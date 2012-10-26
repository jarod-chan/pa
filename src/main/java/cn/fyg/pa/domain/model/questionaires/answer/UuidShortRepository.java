package cn.fyg.pa.domain.model.questionaires.answer;

import java.util.List;

public interface UuidShortRepository {
	
	UuidShort save(UuidShort uuidShort);
	
	UuidShort find(Long id);
	
	List<UuidShort> findByUuidAndQtidAndPartid(String uuid,Long qtid,Long partid);

}
