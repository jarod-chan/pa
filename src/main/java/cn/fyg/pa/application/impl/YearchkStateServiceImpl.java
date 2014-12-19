package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.YearchkStateService;
import cn.fyg.pa.domain.model.yearchkstate.CheckState;
import cn.fyg.pa.domain.model.yearchkstate.YearchkState;
import cn.fyg.pa.domain.model.yearchkstate.YearchkStateFactory;
import cn.fyg.pa.domain.model.yearchkstate.YearchkStateRepository;

@Service
public class YearchkStateServiceImpl implements YearchkStateService {
	
	@Resource
    YearchkStateRepository yearchkStateRepository;

	@Override
	@Transactional
	public YearchkState commitYearchk(Long year, Long personId) {
		YearchkState yearchkState = yearchkStateRepository.findByYearAndPersonId(year, personId);
		if(yearchkState==null){
			yearchkState=YearchkStateFactory.create(year, personId);
		}
		yearchkState.setCheckState(CheckState.commit);
		return yearchkStateRepository.save(yearchkState);
	}

	@Override
	public boolean isCommit(Long year, Long personId) {
		YearchkState yearchkState = yearchkStateRepository.findByYearAndPersonId(year, personId);
		if(yearchkState==null){
			return false;
		}
		return yearchkState.getCheckState()==CheckState.commit;
	}

}
