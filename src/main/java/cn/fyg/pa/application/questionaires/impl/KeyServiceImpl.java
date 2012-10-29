package cn.fyg.pa.application.questionaires.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public Key find(String keystr) {
		return keyRepository.find(keystr);
	}

	@Override
	@Transactional
	public void used(String keystr) {
		Key key = keyRepository.find(keystr);
		key.setState(KeyState.used);
	}

	@Override
	@Transactional
	public void finish(String keystr) {
		Key key = keyRepository.find(keystr);
		key.setState(KeyState.finish);
	}

	@Override
	@Transactional
	public void invalid(String keystr) {
		Key key = keyRepository.find(keystr);
		key.setState(KeyState.invalid);
	}

	@Override
	public Map<KeyState, Integer> getKeyStateCount(Long qtid) {
		List<Object[]> keyStateCount = keyRepository.getKeyStateCount(qtid);
		 Map<KeyState, Integer> map=new HashMap<KeyState, Integer>();
		 for (Object[] objects : keyStateCount) {
			 Long count=(Long)objects[1];
			 map.put((KeyState)objects[0], count.intValue());
		}
		 return map;
	}

	@Override
	@Transactional
	public void save(List<Key> Keys) {
		for (Key key : Keys) {
			keyRepository.save(key);
		}
	}

}
