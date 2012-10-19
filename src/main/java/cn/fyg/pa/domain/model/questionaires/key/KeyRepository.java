package cn.fyg.pa.domain.model.questionaires.key;

public interface KeyRepository {
	
	Key find(String key);
	
	Key save(Key key);

}
