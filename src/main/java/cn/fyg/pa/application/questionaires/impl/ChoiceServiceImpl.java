package cn.fyg.pa.application.questionaires.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.questionaires.ChoiceService;
import cn.fyg.pa.domain.model.questionaires.problem.Choice;
import cn.fyg.pa.domain.model.questionaires.problem.ChoiceRepository;

@Service
public class ChoiceServiceImpl implements ChoiceService {
	
	@Resource
	ChoiceRepository choiceRepository;

	@Override
	public List<Choice> findPartChoice(Long qtid, Long partid) {
		return choiceRepository.findByQtidAndPartid(qtid, partid);
	}

}
