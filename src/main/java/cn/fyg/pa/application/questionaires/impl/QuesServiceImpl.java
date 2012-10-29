package cn.fyg.pa.application.questionaires.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.questionaires.QuesService;
import cn.fyg.pa.domain.model.questionaires.ques.Ques;
import cn.fyg.pa.domain.model.questionaires.ques.QuesRepository;
import cn.fyg.pa.domain.model.questionaires.ques.QuesState;

@Service
public class QuesServiceImpl implements QuesService {
	
	@Resource
	QuesRepository quesRepository;

	@Override
	public Ques find(Long id) {
		return quesRepository.find(id);
	}

	@Override
	public List<Ques> findAll() {
		return quesRepository.findAll();
	}

	@Override
	@Transactional
	public void finish(Long id) {
		Ques ques = quesRepository.find(id);
		ques.setState(QuesState.finish);
	}

}
