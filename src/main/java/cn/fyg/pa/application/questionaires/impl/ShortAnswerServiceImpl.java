package cn.fyg.pa.application.questionaires.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.questionaires.ShortAnswerService;
import cn.fyg.pa.domain.model.questionaires.answer.UuidShort;
import cn.fyg.pa.domain.model.questionaires.answer.UuidShortFactory;
import cn.fyg.pa.domain.model.questionaires.answer.UuidShortRepository;

@Service
public class ShortAnswerServiceImpl implements ShortAnswerService {
	
	@Resource
	UuidShortRepository uuidShortRepository;

	@Override
	public UuidShort create(String uuid, Long qtid, Long partid) {
		return UuidShortFactory.create(uuid, qtid, partid);
	}

	@Override
	@Transactional
	public void save(List<UuidShort> uuidShorts) {
		for (UuidShort uuidShort : uuidShorts) {
			uuidShortRepository.save(uuidShort);
		}
	}

	@Override
	public List<UuidShort> findUuidShort(String uuid, Long qtid, Long partid) {
		return uuidShortRepository.findByUuidAndQtidAndPartid(uuid, qtid, partid);
	}

	@Override
	public UuidShort find(Long id) {
		return uuidShortRepository.find(id);
	}

}
