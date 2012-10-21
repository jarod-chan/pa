package cn.fyg.pa.application.questionaires.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.questionaires.QuesService;
import cn.fyg.pa.domain.model.questionaires.ques.Ques;
import cn.fyg.pa.domain.model.questionaires.ques.QuesRepository;

@Service
public class QuesServiceImpl implements QuesService {
	
	@Resource
	QuesRepository quesRepository;

	@Override
	public Ques find(Long id) {
		return quesRepository.find(id);
	}

}
