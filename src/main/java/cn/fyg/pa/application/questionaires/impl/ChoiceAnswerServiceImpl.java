package cn.fyg.pa.application.questionaires.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.questionaires.ChoiceAnswerService;
import cn.fyg.pa.domain.model.questionaires.answer.UuidAnswer;
import cn.fyg.pa.domain.model.questionaires.answer.UuidAnswerFactory;
import cn.fyg.pa.domain.model.questionaires.answer.UuidAnswerRepository;

@Service
public class ChoiceAnswerServiceImpl implements ChoiceAnswerService {
	
	@Resource
	UuidAnswerRepository uuidAnswerRepository;

	@Override
	@Transactional
	public UuidAnswer save(UuidAnswer uuidAnswer) {
		return uuidAnswerRepository.save(uuidAnswer);
	}

	@Override
	@Transactional
	public void save(List<UuidAnswer> uuidAnswers) {
		for (UuidAnswer uuidAnswer : uuidAnswers) {
			uuidAnswerRepository.save(uuidAnswer);
		}
	}

	@Override
	public List<UuidAnswer> findAnswer(String uuid, Long qtid, Long partid) {
		return uuidAnswerRepository.findByUuidAndQtidAndPartid(uuid, qtid, partid);
	}

	@Override
	public UuidAnswer create(String uuid, Long qtid, Long partid) {
		return UuidAnswerFactory.create(uuid,qtid,partid);
	}

	@Override
	public UuidAnswer find(Long id) {
		return uuidAnswerRepository.find(id);
	}

}
