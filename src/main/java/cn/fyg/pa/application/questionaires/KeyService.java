package cn.fyg.pa.application.questionaires;

import cn.fyg.pa.domain.model.questionaires.key.Key;

public interface KeyService {
	
	boolean checkKey(String keystr);
	
	Key find(String keystr);

}
