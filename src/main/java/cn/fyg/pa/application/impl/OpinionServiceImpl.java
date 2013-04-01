package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.OpinionService;
import cn.fyg.pa.domain.model.atten.opinion.Opinion;
import cn.fyg.pa.domain.model.atten.opinion.OpinionRepository;

@Service("opinionService")
public class OpinionServiceImpl implements OpinionService {
	
	@Resource
	OpinionRepository opinionRepository;

	@Override
	@Transactional
	public Opinion append(Opinion opinion) {
		return opinionRepository.save(opinion);
	}

	@Override
	public List<Opinion> ListOpinions(String businessCode, Long businessId) {
		return opinionRepository.findByBusinessCodeAndBusinessIdOrderByIdAsc(businessCode, businessId);
	}

}
