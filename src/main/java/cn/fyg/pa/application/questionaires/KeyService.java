package cn.fyg.pa.application.questionaires;

import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.questionaires.key.Key;
import cn.fyg.pa.domain.model.questionaires.key.KeyState;

public interface KeyService {
	
	boolean checkKey(String keystr);
	
	void save(List<Key> Keys);
	
	Key find(String keystr);
	
	void used(String keystr);
	
	void finish(String keystr);
	
	void invalid(String keystr);
	
	Map<KeyState,Integer> getKeyStateCount(Long qtid);
}
