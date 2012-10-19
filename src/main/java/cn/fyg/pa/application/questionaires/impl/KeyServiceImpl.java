package cn.fyg.pa.application.questionaires.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.questionaires.KeyService;
import cn.fyg.pa.domain.model.questionaires.key.Key;
import cn.fyg.pa.domain.model.questionaires.key.KeyRepository;
import cn.fyg.pa.domain.model.questionaires.key.KeyState;

@Service
public class KeyServiceImpl implements KeyService{
	
	@Resource
	KeyRepository keyRepository;

	@Override
	public boolean checkKey(String keystr) {
		Key key = keyRepository.find(keystr);
		if(key==null){
			return false;
		}
		return key.getState()!=KeyState.invalid;
	}

}
