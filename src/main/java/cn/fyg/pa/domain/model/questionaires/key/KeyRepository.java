package cn.fyg.pa.domain.model.questionaires.key;

import java.util.List;

public interface KeyRepository {
	
	Key find(String key);
	
	Key save(Key key);
	
	List<Object[]> getKeyStateCount(Long qtid);

}
