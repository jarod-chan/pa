package cn.fyg.pa.application.questionaires.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.questionaires.ShortService;
import cn.fyg.pa.domain.model.questionaires.problem.ShortAnswer;
import cn.fyg.pa.domain.model.questionaires.problem.ShortAnswerRepository;

@Service
public class ShortServiceImpl implements ShortService {
	
	@Resource
	ShortAnswerRepository shortAnswerRepository;

	@Override
	public List<ShortAnswer> findPartShort(Long qtid, Long partid) {
		return shortAnswerRepository.findByQtidAndPartid(qtid, partid);
	}

}
